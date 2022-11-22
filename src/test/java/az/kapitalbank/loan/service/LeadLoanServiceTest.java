package az.kapitalbank.loan.service;

import static az.kapitalbank.loan.constants.TestConstant.LEAD_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import az.kapitalbank.loan.constants.Status;
import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.dto.LeadStatusDto;
import az.kapitalbank.loan.dto.response.LeadResponseDto;
import az.kapitalbank.loan.dto.response.WrapperResponse;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.entity.LeadSourceEntity;
import az.kapitalbank.loan.exception.CommonException;
import az.kapitalbank.loan.mapper.LeadLoanMapper;
import az.kapitalbank.loan.message.optimus.listener.LeadStatusListener;
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
    @Mock
    LeadStatusListener leadStatusListener;
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
                .id(LEAD_ID.getValue())
                .status(Status.WAITING)
                .build();
        var leadLoanEvent = LeadLoanEvent.builder()
                .source(LeadSource.builder().code(leadSource).build())
                .amount(BigDecimal.ONE)
                .build();
        when(leadSourceRepository.findById(leadSource)).thenReturn(
                Optional.of(leadSourceEntity));
        when(leadLoanMapper.toLoanEntity(leadLoanRequestDto, leadSourceEntity)).thenReturn(
                loanEntity);
        when(leadLoanRepository.saveAndFlush(loanEntity)).thenReturn(loanEntity);
        when(leadLoanMapper.toLeadLoanModel(loanEntity, leadSourceEntity)).thenReturn(
                leadLoanEvent);
        var expected = WrapperResponse.builder()
                .data(new LeadResponseDto(LEAD_ID.getValue())).build();
        var actual = leadLoanService.saveLead(leadLoanRequestDto, leadSource);
        assertEquals(expected, actual);
    }

    @Test
    void saveLead_SourceStatusFalse_ShouldCommentException() {
        String leadSource = "0014";
        var leadLoanRequestDto = LeadLoanRequestDto.builder()
                .address("")
                .build();
        var leadSourceEntity = LeadSourceEntity.builder()
                .code(leadSource)
                .status(false)
                .name("terminal")
                .build();
        when(leadSourceRepository.findById(leadSource)).thenReturn(
                Optional.ofNullable(leadSourceEntity));

        assertThrows(CommonException.class,
                () -> leadLoanService.saveLead(leadLoanRequestDto, leadSource));
    }

    @Test
    void saveLead_SourceNotFound_ShouldCommentException() {
        String leadSource = "0014";
        var leadLoanRequestDto = LeadLoanRequestDto.builder()
                .address("")
                .build();
        var leadSourceEntity = LeadSourceEntity.builder()
                .code(leadSource)
                .status(true)
                .name("terminal")
                .build();
        when(leadSourceRepository.findById(leadSource)).thenReturn(
                Optional.empty());

        assertThrows(CommonException.class,
                () -> leadLoanService.saveLead(leadLoanRequestDto, leadSource));
    }

    @Test
    void saveLeadSourceNotActiveException() {
        String leadSource = "0014";
        var leadSourceEntity = LeadSourceEntity.builder()
                .code(leadSource)
                .build();
        var leadLoanRequestDto = LeadLoanRequestDto.builder()
                .build();
        when(leadSourceRepository.findById(leadSource)).thenReturn(
                Optional.of(leadSourceEntity));
        assertThrows(CommonException.class,
                () -> leadLoanService.saveLead(leadLoanRequestDto, leadSource));
        verify(leadSourceRepository).findById(leadSource);
    }

    @Test
    void saveLeadSourceNotFoundException() {
        String leadSource = "0014";
        var leadSourceEntity = LeadSourceEntity.builder()
                .code(leadSource)
                .build();
        var leadLoanRequestDto = LeadLoanRequestDto.builder()
                .build();
        when(leadSourceRepository.findById(leadSource)).thenReturn(
                Optional.empty());
        assertThrows(CommonException.class,
                () -> leadLoanService.saveLead(leadLoanRequestDto, leadSource));
        verify(leadSourceRepository).findById(leadSource);
    }

    @Test
    void updateLeadStatusSuccess() {
        var leadLoanEntity = LeadLoanEntity.builder()
                .id("85c0407c-3d94-11ed-b878-0242ac120002")
                .status(Status.WAITING)
                .build();
        var leadStatusDto = LeadStatusDto.builder()
                .id("85c0407c-3d94-11ed-b878-0242ac120002")
                .status(Status.ACCEPTED)
                .build();
        when(leadLoanRepository.findById("85c0407c-3d94-11ed-b878-0242ac120002")).thenReturn(
                Optional.ofNullable(leadLoanEntity));
        leadLoanService.updateLeadStatus(leadStatusDto);
        verify(leadLoanRepository).findById(leadLoanEntity.getId());
    }

    @Test
    void updateLeadStatusException() {
        var leadLoanEntity = LeadLoanEntity.builder()
                .id("85c0407c-3d94-11ed-b878-0242ac120002")
                .status(Status.WAITING)
                .build();
        var leadStatusDto = LeadStatusDto.builder()
                .id("85c0407c-3d94-11ed-b878-0242ac120002")
                .status(Status.ACCEPTED)
                .build();
        when(leadLoanRepository.findById("85c0407c-3d94-11ed-b878-0242ac120002")).thenReturn(
                Optional.empty());
        leadLoanService.updateLeadStatus(leadStatusDto);
        verify(leadLoanRepository).findById(leadLoanEntity.getId());
    }
}
