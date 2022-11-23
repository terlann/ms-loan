package az.kapitalbank.loan.service;

import static az.kapitalbank.loan.constants.TestConstant.ADDRESS;
import static az.kapitalbank.loan.constants.TestConstant.LEAD_ID;
import static az.kapitalbank.loan.constants.TestConstant.LEAD_SOURCE;
import static az.kapitalbank.loan.constants.TestConstant.PHONE_NUMBER;
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
        var leadLoanRequestDto = LeadLoanRequestDto.builder()
                .address(ADDRESS.getValue())
                .build();
        var leadSourceEntity = LeadSourceEntity.builder()
                .code(LEAD_SOURCE.getValue())
                .status(true)
                .name("terminal")
                .build();
        var loanEntity = LeadLoanEntity.builder()
                .id(LEAD_ID.getValue())
                .status(Status.WAITING)
                .build();
        var leadLoanEvent = LeadLoanEvent.builder()
                .source(LeadSource.builder().code(LEAD_SOURCE.getValue()).build())
                .amount(BigDecimal.ONE)
                .build();
        when(leadSourceRepository.findById(LEAD_SOURCE.getValue())).thenReturn(
                Optional.of(leadSourceEntity));
        when(leadLoanMapper.toLoanEntity(leadLoanRequestDto, leadSourceEntity)).thenReturn(
                loanEntity);
        when(leadLoanRepository.saveAndFlush(loanEntity)).thenReturn(loanEntity);
        when(leadLoanMapper.toLeadLoanModel(loanEntity, leadSourceEntity)).thenReturn(
                leadLoanEvent);
        var expected = WrapperResponse.builder()
                .data(new LeadResponseDto(LEAD_ID.getValue())).build();
        var actual = leadLoanService.saveLead(leadLoanRequestDto, LEAD_SOURCE.getValue());
        assertEquals(expected, actual);
    }

    @Test
    void saveLead_SourceStatusFalse_ShouldCommonException() {
        var leadLoanRequestDto = LeadLoanRequestDto.builder()
                .address(ADDRESS.getValue())
                .phoneNumber(PHONE_NUMBER.getValue())
                .build();
        var leadSourceEntity = LeadSourceEntity.builder()
                .code(LEAD_ID.getValue())
                .status(false)
                .name("terminal")
                .build();
        when(leadSourceRepository.findById(LEAD_SOURCE.getValue())).thenReturn(
                Optional.ofNullable(leadSourceEntity));

        assertThrows(CommonException.class,
                () -> leadLoanService.saveLead(leadLoanRequestDto, LEAD_SOURCE.getValue()));
    }

    @Test
    void saveLead_SourceNotFound_ShouldCommonException() {
        var leadLoanRequestDto = LeadLoanRequestDto.builder()
                .address(ADDRESS.getValue())
                .phoneNumber(PHONE_NUMBER.getValue())
                .build();
        when(leadSourceRepository.findById(LEAD_SOURCE.getValue())).thenReturn(
                Optional.empty());

        assertThrows(CommonException.class,
                () -> leadLoanService.saveLead(leadLoanRequestDto, LEAD_SOURCE.getValue()));
    }

    @Test
    void saveLeadSourceNotActiveException() {
        var leadSourceEntity = LeadSourceEntity.builder()
                .code(LEAD_SOURCE.getValue())
                .build();
        var leadLoanRequestDto = LeadLoanRequestDto.builder()
                .build();
        when(leadSourceRepository.findById(LEAD_SOURCE.getValue())).thenReturn(
                Optional.of(leadSourceEntity));
        assertThrows(CommonException.class,
                () -> leadLoanService.saveLead(leadLoanRequestDto, LEAD_SOURCE.getValue()));
        verify(leadSourceRepository).findById(LEAD_SOURCE.getValue());
    }

    @Test
    void saveLeadSourceNotFoundException() {
        var leadLoanRequestDto = LeadLoanRequestDto.builder()
                .build();
        when(leadSourceRepository.findById(LEAD_SOURCE.getValue())).thenReturn(
                Optional.empty());
        assertThrows(CommonException.class,
                () -> leadLoanService.saveLead(leadLoanRequestDto, LEAD_SOURCE.getValue()));
        verify(leadSourceRepository).findById(LEAD_SOURCE.getValue());
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
