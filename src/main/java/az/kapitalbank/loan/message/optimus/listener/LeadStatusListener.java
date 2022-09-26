package az.kapitalbank.loan.message.optimus.listener;

import az.kapitalbank.loan.message.optimus.model.LeadStatusEvent;
import az.kapitalbank.loan.service.LeadLoanService;
import java.util.Objects;
import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LeadStatusListener {

    LeadLoanService leadLoanService;

    @Bean
    public Consumer<LeadStatusEvent> leadStatusResult() {
        return event -> {
            if (Objects.nonNull(event)) {
                log.info("Optimus result lead status event consumer is started. Event - {}", event);
                leadLoanService.updateleadStatus(event);
            }
        };
    }
}
