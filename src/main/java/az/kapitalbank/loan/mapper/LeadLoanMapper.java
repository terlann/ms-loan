package az.kapitalbank.loan.mapper;

import az.kapitalbank.loan.constants.LeadStatus;
import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.entity.LeadSourceEntity;
import az.kapitalbank.loan.message.model.LeadLoanEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LeadLoanMapper {

    @Mapping(target = "source", source = "leadSource.code")
    @Mapping(target = "status", ignore = true)
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
    LeadLoanEvent toLeadLoanModel(LeadLoanEntity leadLoanEntity, LeadSourceEntity leadSourceEntity);


}
