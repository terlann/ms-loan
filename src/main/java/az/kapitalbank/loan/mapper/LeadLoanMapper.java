package az.kapitalbank.loan.mapper;

import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.dto.LeadStatusDto;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.entity.LeadSourceEntity;
import az.kapitalbank.loan.message.optimus.model.LeadStatusEvent;
import az.kapitalbank.loan.message.telesales.model.LeadLoanEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LeadLoanMapper {

    @Mapping(target = "source", source = "leadSource.code")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "fraudReason", source = "leadLoanRequestDto.leadComment")
    @Mapping(target = "customerCif", source = "leadLoanRequestDto.cif")
    @Mapping(target = "customerCardPan", source = "leadLoanRequestDto.cardPan")
    @Mapping(target = "couponCode", source = "leadLoanRequestDto.couponCode")
    @Mapping(target = "umicoUserId", source = "leadLoanRequestDto.umicoUserId")
    @Mapping(target = "customerWorkplace", source = "leadLoanRequestDto.workplace")
    @Mapping(target = "customerAddress", source = "leadLoanRequestDto.address")
    LeadLoanEntity toLoanEntity(LeadLoanRequestDto leadLoanRequestDto, LeadSourceEntity leadSource);

    @Mapping(target = "customer.fullName", source = "leadLoanEntity.fullname")
    @Mapping(target = "customer.pin", source = "leadLoanEntity.pincode")
    @Mapping(target = "customer.phoneNumber", source = "leadLoanEntity.phoneNumber")
    @Mapping(target = "customer.address", source = "leadLoanEntity.customerAddress")
    @Mapping(target = "customer.workplace", source = "leadLoanEntity.customerWorkplace")
    @Mapping(target = "customer.pan", source = "leadLoanEntity.customerCardPan")
    @Mapping(target = "mkrAndGovAgreement", source = "leadLoanEntity.isAgreement")
    @Mapping(target = "amount", source = "leadLoanEntity.productAmount")
    @Mapping(target = "duration", source = "leadLoanEntity.productDuration")
    @Mapping(target = "source.code", source = "leadSourceEntity.code")
    @Mapping(target = "source.name", source = "leadSourceEntity.name")
    @Mapping(target = "comment", source = "leadLoanEntity.fraudReason")
    @Mapping(target = "customer.cif", source = "leadLoanEntity.customerCif")
    @Mapping(target = "otherProcessId", source = "leadLoanEntity.otherProcessId")
    az.kapitalbank.loan.message.optimus.model.LeadLoanEvent toLeadLoanModel(
            LeadLoanEntity leadLoanEntity, LeadSourceEntity leadSourceEntity);

    @Mapping(target = "phoneNumber", source = "leadLoanEvent.cnsAddress")
    LeadLoanRequestDto toLeadLoanRequestDto(LeadLoanEvent leadLoanEvent);

    LeadStatusDto toLeadStatusDto(LeadStatusEvent event);

}
