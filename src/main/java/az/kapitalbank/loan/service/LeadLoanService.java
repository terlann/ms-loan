package az.kapitalbank.loan.service;

import az.kapitalbank.loan.constants.LeadStatus;
import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.dto.response.SaveLeadResponseDto;
import az.kapitalbank.loan.dto.response.WrapperResponse;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.entity.LeadSourceEntity;
import az.kapitalbank.loan.exception.model.SourceNotFoundException;
import az.kapitalbank.loan.mapper.LeadLoanMapper;
import az.kapitalbank.loan.message.LeadLoanSender;
import az.kapitalbank.loan.message.model.LeadLoanEvent;
import az.kapitalbank.loan.repository.LeadLoanRepository;
import az.kapitalbank.loan.repository.LeadSourceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LeadLoanService {

    LeadLoanMapper leadLoanMapper;
    LeadLoanRepository leadLoanRepository;
    LeadLoanSender leadLoanSender;
    LeadSourceRepository leadSourceRepository;

    @Transactional
    public WrapperResponse saveLead(LeadLoanRequestDto leadLoanRequestDto, String leadSource) {
        log.info("save lead in db start... Request - {}, Lead-Source - [{}]", leadLoanRequestDto, leadSource);
        Optional<LeadSourceEntity> source = leadSourceRepository.findById(leadSource);
        if (!source.isPresent()) {
            throw new SourceNotFoundException(leadSource);
        }
        LeadLoanEntity loanEntity = leadLoanMapper.toLoanEntity(leadLoanRequestDto, source.get());
        loanEntity.setStatus(LeadStatus.WAITING);
        loanEntity.setInsertedDate(LocalDate.now());

        LeadLoanEntity leadLoanEntityResult = leadLoanRepository.save(loanEntity);
        LeadLoanEvent leadLoanEvent = leadLoanMapper.toLeadLoanModel(leadLoanEntityResult);
        sendLeadWithMessaging(leadLoanEvent);
        SaveLeadResponseDto saveLeadResponseDto = new SaveLeadResponseDto();
        saveLeadResponseDto.setLeadId(String.valueOf(leadLoanEntityResult.getId()));
        WrapperResponse<SaveLeadResponseDto> response = WrapperResponse.<SaveLeadResponseDto>builder()
                .data(saveLeadResponseDto)
                .build();
        return response;
    }

    public void sendLeadWithMessaging(LeadLoanEvent leadLoanEvent) {
        log.info("lead send with message. lead - {}", leadLoanEvent.toString());
        leadLoanSender.sendMessage(leadLoanEvent);
    }
}
