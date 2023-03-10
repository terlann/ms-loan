package az.kapitalbank.loan.scheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import az.kapitalbank.loan.constants.Status;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.entity.LeadSourceEntity;
import az.kapitalbank.loan.mapper.LeadLoanMapper;
import az.kapitalbank.loan.message.optimus.model.LeadLoanEvent;
import az.kapitalbank.loan.message.optimus.publisher.LeadLoanSender;
import az.kapitalbank.loan.repository.LeadLoanRepository;
import az.kapitalbank.loan.repository.LeadSourceRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class LoanSchedulerTest {

    private static final List<String> sources = List.of("0008");
    @Mock
    LeadLoanMapper leadLoanMapper;
    @Mock
    LeadLoanRepository leadLoanRepository;
    @Mock
    LeadSourceRepository sourceRepository;
    @Mock
    LeadLoanSender leadLoanSender;

    @InjectMocks
    LoanScheduler loanScheduler;

    @Test
    void sendToKafkaFromDb() {
        ReflectionTestUtils.setField(loanScheduler, "sources", sources);

        var leadSource = LeadSourceEntity.builder()
                .name("8196")
                .code("0008")
                .build();

        when(sourceRepository.findById(sources.get(0)))
                .thenReturn(Optional.of(leadSource));

        var leadLoanEntity = LeadLoanEntity.builder()
                .source("0008")
                .phoneNumber("num")
                .build();


        when(leadLoanRepository.findBySourceAndStatusIsNot(sources.get(0), Status.SENDING))
                .thenReturn(List.of(leadLoanEntity));

        var loanEvent = LeadLoanEvent.builder().build();

        when(leadLoanMapper.toLeadLoanModel(leadLoanEntity, leadSource))
                .thenReturn(loanEvent);

        loanScheduler.sendToKafkaFromDb();

        verify(leadLoanSender, times(1))
                .sendMessage(loanEvent);

        verify(leadLoanRepository, times(1))
                .save(leadLoanEntity);

        assertEquals(Status.SENDING,
                leadLoanEntity.getStatus());

    }
}
