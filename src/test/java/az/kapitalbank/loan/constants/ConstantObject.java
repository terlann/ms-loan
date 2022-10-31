package az.kapitalbank.loan.constants;

import static az.kapitalbank.loan.constants.TestConstant.PHONE_NUMBER;

import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.entity.LeadSourceEntity;
import az.kapitalbank.loan.message.optimus.model.CustomerEvent;
import az.kapitalbank.loan.message.optimus.model.LeadLoanEvent;
import az.kapitalbank.loan.message.optimus.model.LeadSource;
import java.math.BigDecimal;

public class ConstantObject {
    public static LeadLoanEntity getLeadLoanEntity(LeadLoanRequestDto leadLoanRequestDto,
                                                   LeadSourceEntity leadSource) {
        var leadLoanEntity = LeadLoanEntity.builder()
                .fullname(leadLoanRequestDto.getFullname())
                .pincode(leadLoanRequestDto.getPincode())
                .phoneNumber(leadLoanRequestDto.getPhoneNumber())
                .productType(leadLoanRequestDto.getProductType())
                .subProductType(leadLoanRequestDto.getSubProductType())
                .productAmount(leadLoanRequestDto.getProductAmount())
                .productDuration(leadLoanRequestDto.getProductDuration().toString())
                .customerCardPan(leadLoanRequestDto.getCardPan())
                .customerCif(leadLoanRequestDto.getCif())
                .customerAddress(leadLoanRequestDto.getAddress())
                .customerWorkplace(leadLoanRequestDto.getWorkplace())
                .campaignName(leadLoanRequestDto.getCampaignName())
                .otherProcessId(leadLoanRequestDto.getOtherProcessId())
                .leadComment(leadLoanRequestDto.getLeadComment())
                .fraudReason(leadLoanRequestDto.getLeadComment())
                .isAgreement(leadLoanRequestDto.getIsAgreement())
                .formalizationMethod(leadLoanRequestDto.getFormalizationMethod())
                .monthlyPayment(leadLoanRequestDto.getMonthlyPayment())
                .source(leadSource.getCode())
                .build();
        return leadLoanEntity;
    }

    public static LeadLoanEntity getLeadLoanEntityForOptimus(
            LeadSourceEntity leadSource) {
        var leadLoanEntity = LeadLoanEntity.builder()
                .fullname("Terlan Qurbanmov")
                .pincode("5JR9R11")
                .phoneNumber("+994773227040")
                .productType(ProductType.UMICO_MARKETPLACE)
                .subProductType(SubProductType.UMICO)
                .productAmount(BigDecimal.ONE)
                .productDuration("6")
                .customerCardPan("4169111122223333")
                .customerCif("0130179")
                .customerAddress("Baki.s/Nizami.k")
                .customerWorkplace("KapitalBank")
                .campaignName("KapitalBank")
                .otherProcessId("5612")
                .leadComment("no comment")
                .fraudReason("pin")
                .isAgreement(true)
                .formalizationMethod(FormalizationMethod.VIDEO_CALL)
                .monthlyPayment(BigDecimal.ONE)
                .source(leadSource.getCode())
                .build();
        return leadLoanEntity;
    }

    public static LeadSourceEntity getLeadSource() {
        return LeadSourceEntity.builder()
                .code("0001")
                .name("Kapitalbank")
                .status(true)
                .build();
    }

    public static LeadLoanRequestDto getLeadLoanRequestDto() {
        return LeadLoanRequestDto.builder()
                .campaignName("KapitalBank")
                .cif("0130111")
                .workplace("Kapitalbank")
                .leadComment("Fraud_PIN")
                .fullname("Terlan Qurbanov")
                .isAgreement(true)
                .monthlyPayment(BigDecimal.ONE)
                .phoneNumber(PHONE_NUMBER.getValue())
                .address("Baki.s")
                .cardPan("123")
                .pincode("5JR9RR")
                .otherProcessId("0000")
                .productAmount(BigDecimal.ONE)
                .productDuration(6)
                .productType(ProductType.UMICO_MARKETPLACE)
                .subProductType(SubProductType.UMICO)
                .formalizationMethod(FormalizationMethod.VIDEO_CALL)
                .build();
    }

    public static LeadLoanEvent getLeadLoanEvent(LeadSourceEntity leadSource) {
        return LeadLoanEvent.builder()
                .customer(CustomerEvent.builder()
                        .cif("0130179")
                        .pan("4169111122223333")
                        .pin("5JR9R11")
                        .address("Baki.s/Nizami.k")
                        .fullName("Terlan Qurbanmov")
                        .workplace("KapitalBank")
                        .phoneNumber("+994773227040")
                        .build())
                .productType(ProductType.UMICO_MARKETPLACE)
                .campaignName("KapitalBank")
                .subProductType(SubProductType.UMICO)
                .amount(BigDecimal.ONE)
                .duration(6)
                .campaignName("KapitalBank")
                .source(LeadSource.builder()
                        .name(leadSource.getName())
                        .code(leadSource.getCode()).build())
                .comment("pin")
                .mkrAndGovAgreement(true)
                .monthlyPayment(BigDecimal.ONE)
                .formalizationMethod(FormalizationMethod.VIDEO_CALL)
                .otherProcessId("5612")
                .build();
    }

    public static az.kapitalbank.loan.message
            .telesales.model.LeadLoanEvent getLeadLoanEventForTeleSales() {
        return az.kapitalbank.loan.message.telesales.model.LeadLoanEvent.builder()
                .id(11L)
                .channel("kb")
                .cmdText("text")
                .cnsAddress("+994773227040")
                .declineReason(0)
                .errorMessage("error nessage")
                .status(1)
                .origTime(1L)
                .build();
    }

    public static LeadLoanRequestDto getLeadLoanRequestDtoForTelesales() {
        return LeadLoanRequestDto.builder()
                .phoneNumber(PHONE_NUMBER.getValue())
                .build();
    }
}
