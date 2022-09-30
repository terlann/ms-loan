package az.kapitalbank.loan.scheduler;

import static lombok.AccessLevel.PRIVATE;

import az.kapitalbank.loan.constants.Status;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.entity.LeadSourceEntity;
import az.kapitalbank.loan.mapper.LeadLoanMapper;
import az.kapitalbank.loan.message.optimus.model.LeadLoanEvent;
import az.kapitalbank.loan.message.optimus.publisher.LeadLoanSender;
import az.kapitalbank.loan.repository.LeadLoanRepository;
import az.kapitalbank.loan.repository.LeadSourceRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@EnableAsync
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT5M")
@Component("schedulerSendToKafka")
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
public class LoanScheduler {

    LeadLoanMapper leadLoanMapper;
    LeadLoanRepository leadLoanRepository;
    LeadSourceRepository sourceRepository;
    LeadLoanSender leadLoanSender;

    @NonFinal
    @Value("#{'${loan.sql.sources}'.split(',')}")
    List<String> sources;

    @Scheduled(cron = "0 30 0-23 * * *")
    @SchedulerLock(name = "Scheduler_Send_To_Kafka")
    @Async("threadPoolExecutor")
    void sendToKafkaFromDb() {
        if (Objects.nonNull(sources)) {
            log.info("STARTING SCHEDULER DATE : {}", LocalDate.now());
            sources.forEach(source -> {

                Optional<LeadSourceEntity> optionalLeadSource = sourceRepository.findById(source);

                if (optionalLeadSource.isPresent()) {

                    var leadSource = optionalLeadSource.get();
                    log.info("STARTING SENDING DATA FROM DB SOURCE : {}", leadSource.getName());

                    List<LeadLoanEntity> loans =
                            leadLoanRepository.findBySourceAndStatusIsNot(source,
                                    Status.SENDING);

                    if (Objects.nonNull(loans)) {

                        loans.forEach(leadLoanEntity -> {
                            log.info("STARTING SENDING DATA FROM DB LEAD : {}", leadLoanEntity);
                            sendLeadWithMessaging(
                                    leadLoanMapper.toLeadLoanModel(leadLoanEntity, leadSource));
                            leadLoanEntity.setStatus(Status.SENDING);
                            leadLoanRepository.save(leadLoanEntity);
                            log.info("ENDING SENDING DATA FROM DB LEAD : {}", leadLoanEntity);
                        });

                    }
                }

            });

            log.info("ENDING SCHEDULER DATE : {}", LocalDate.now());

        }
    }

    private void sendLeadWithMessaging(LeadLoanEvent leadLoanEvent) {
        log.info("LEAD SEND EVENT: {}", leadLoanEvent);
        leadLoanSender.sendMessage(leadLoanEvent);
    }

}
