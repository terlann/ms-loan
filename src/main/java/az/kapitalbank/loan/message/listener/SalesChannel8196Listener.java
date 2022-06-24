package az.kapitalbank.loan.message.listener;

import static az.kapitalbank.loan.constants.SalesChannel.SOURCE_8196;

import az.kapitalbank.loan.mapper.LeadLoanMapper;
import az.kapitalbank.loan.message.model.SalesChannel8196Event;
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
public class SalesChannel8196Listener {
    LeadLoanService leadLoanService;
    LeadLoanMapper leadLoanMapper;

    @Bean
    public Consumer<SalesChannel8196Event> salesChannelResult() {
        return event -> {
            if (Objects.nonNull(event)) {
                log.info("Sales channel 8196 event onsumer is started. Event - {}", event);
                leadLoanService.saveLead(leadLoanMapper.toLeadLoanRequestDto(event), SOURCE_8196);
            }
        };
    }

}
