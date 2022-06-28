package az.kapitalbank.loan.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import az.kapitalbank.loan.constants.LeadStatus;
import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.entity.LeadSourceEntity;
import az.kapitalbank.loan.mapper.LeadLoanMapper;
import az.kapitalbank.loan.message.optimus.model.LeadLoanEvent;
import az.kapitalbank.loan.message.optimus.model.LeadSource;
import az.kapitalbank.loan.message.optimus.publisher.LeadLoanSender;
import az.kapitalbank.loan.repository.LeadLoanRepository;
import az.kapitalbank.loan.repository.LeadSourceRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LeadLoanServiceTest {
    @Mock
    LeadLoanMapper leadLoanMapper;
    @Mock
    LeadLoanRepository leadLoanRepository;
    @Mock
    LeadLoanSender leadLoanSender;
    @Mock
    LeadSourceRepository leadSourceRepository;
    @InjectMocks
    LeadLoanService leadLoanService;

    @Test
    void saveLead() {
        String leadSource = "0014";
        var leadLoanRequestDto = LeadLoanRequestDto.builder()
                .address("")
                .build();
        var leadSourceEntity = LeadSourceEntity.builder()
                .code(leadSource)
                .status(true)
                .name("terminal")
                .build();
        var loanEntity = LeadLoanEntity.builder()
                .status(LeadStatus.WAITING)
                .build();
        var leadLoanEvent = LeadLoanEvent.builder()
                .source(LeadSource.builder().code(leadSource).build())
                .amount(BigDecimal.ONE)
                .build();


        when(leadSourceRepository.findById(leadSource)).thenReturn(
                Optional.of(leadSourceEntity));
        when(leadLoanMapper.toLoanEntity(leadLoanRequestDto, leadSourceEntity)).thenReturn(
                loanEntity);
        when(leadLoanRepository.save(loanEntity)).thenReturn(loanEntity);
        when(leadLoanMapper.toLeadLoanModel(loanEntity, leadSourceEntity)).thenReturn(
                leadLoanEvent);

        leadLoanService.saveLead(leadLoanRequestDto, leadSource);
        verify(leadSourceRepository).findById(leadSource);
    }
}
