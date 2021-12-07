package az.kapitalbank.loan.message;

import az.kapitalbank.loan.message.model.LeadLoanEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.function.Supplier;

@Slf4j
@Component
public class LeadLoanSender {
    LinkedList<LeadLoanEvent> leadLoanEventLinkedList = new LinkedList<>();

    public void sendMessage(LeadLoanEvent leadLoanEvent) {
        if (leadLoanEventLinkedList == null) {
            leadLoanEventLinkedList = new LinkedList<>();
        }
        leadLoanEventLinkedList.push(leadLoanEvent);
    }


    @Bean
    private Supplier<Message<LeadLoanEvent>> produceLead() {
        return () -> {
            if (leadLoanEventLinkedList.peek() != null) {
                Message<LeadLoanEvent> message = MessageBuilder.withPayload(leadLoanEventLinkedList.peek()).build();
                leadLoanEventLinkedList.poll();
                log.info("lead send to optimus SUCCESS. Message - {}", message.getPayload());
                return message;
            } else {
                return null;
            }
        };
    }
}
