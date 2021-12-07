package az.kapitalbank.loan.mapper;

import az.kapitalbank.loan.constants.LeadSource;
import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.message.model.LeadLoanEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeadLoanMapper {

    LeadLoanMapper INSTANCE = Mappers.getMapper(LeadLoanMapper.class);


    @Mapping(target = "source", source = "leadSource")
    LeadLoanEntity toLoanEntity(LeadLoanRequestDto leadLoanRequestDto, LeadSource leadSource);

    @Mapping(target = "customerEvent.fullname", source = "fullname")
    @Mapping(target = "customerEvent.pincode", source = "pincode")
    @Mapping(target = "customerEvent.phoneNumber", source = "phoneNumber")
    @Mapping(target = "customerEvent.address", source = "customerAddress")
    @Mapping(target = "customerEvent.workplace", source = "customerWorkplace")
    @Mapping(target = "customerEvent.pan", source = "customerCardPan")
    @Mapping(target = "mkrAndGovAgreement", source = "isAgreement")
    @Mapping(target = "amount", source = "productAmount")
    @Mapping(target = "duration", source = "productDuration")
    LeadLoanEvent toLeadLoanModel(LeadLoanEntity leadLoanEntity);


}
