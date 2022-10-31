package az.kapitalbank.loan.service;

import az.kapitalbank.loan.constants.Error;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.Status;
import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.dto.LeadStatusDto;
import az.kapitalbank.loan.dto.response.LeadResponseDto;
import az.kapitalbank.loan.dto.response.WrapperResponse;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.entity.LeadSourceEntity;
import az.kapitalbank.loan.exception.CommonException;
import az.kapitalbank.loan.mapper.LeadLoanMapper;
import az.kapitalbank.loan.message.optimus.model.LeadLoanEvent;
import az.kapitalbank.loan.message.optimus.publisher.LeadLoanSender;
import az.kapitalbank.loan.repository.LeadLoanRepository;
import az.kapitalbank.loan.repository.LeadSourceRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public WrapperResponse<LeadResponseDto> saveLead(LeadLoanRequestDto leadLoanRequestDto,
                                                     String leadSource) {
        log.info("save lead start: Request - {}, source - [{}]", leadLoanRequestDto, leadSource);

        LeadSourceEntity source = leadSourceRepository.findById(leadSource).orElseThrow(
                () -> new CommonException(Error.SOURCE_NOT_FOUND_EXCEPTION,
                        "Source not found exception : mobileNumber - "
                                + leadLoanRequestDto.getPhoneNumber()));

        if (!source.isStatus()) {
            throw new CommonException(Error.SOURCE_NOT_ACTIVE_EXCEPTION,
                    "Source not active exception: phoneNumber - "
                            + leadLoanRequestDto.getPhoneNumber());
        }

        LeadLoanEntity loanEntity = leadLoanMapper.toLoanEntity(leadLoanRequestDto, source);
        loanEntity.setStatus(Status.WAITING);
        loanEntity.setInsertedDate(LocalDateTime.now());

        LeadLoanEntity leadLoanEntityResult = leadLoanRepository.save(loanEntity);
        LeadLoanEvent leadLoanEvent =
                leadLoanMapper.toLeadLoanModel(leadLoanEntityResult, source);
        if (Objects.isNull(leadLoanEvent.getProductType())) {
            leadLoanEvent.setProductType(ProductType.NONE);
        }
        sendLeadWithMessaging(leadLoanEvent);
        LeadResponseDto leadResponseDto =
                LeadResponseDto.builder().leadId(leadLoanEntityResult.getId()).build();
        log.info("save lead finish: Request - {}, lead-id - [{}]", leadLoanRequestDto,
                leadLoanEntityResult.getId());

        return WrapperResponse.<LeadResponseDto>builder()
                .data(leadResponseDto)
                .build();
    }

    private void sendLeadWithMessaging(LeadLoanEvent leadLoanEvent) {
        log.info("lead send event: {}", leadLoanEvent.toString());
        leadLoanSender.sendMessage(leadLoanEvent);
    }

    @Transactional
    public void updateLeadStatus(LeadStatusDto leadStatusDto) {
        log.info("Lead status update process was started. leadStatusEvent: {}", leadStatusDto);
        var leadLoanEntity = leadLoanRepository.findById(leadStatusDto.getId());
        if (leadLoanEntity.isEmpty()) {
            log.info("Lead status update process was failed. leadStatusEvent: {}", leadStatusDto);
            return;
        }
        leadLoanEntity.get().setStatus(leadStatusDto.getStatus());
    }
}
