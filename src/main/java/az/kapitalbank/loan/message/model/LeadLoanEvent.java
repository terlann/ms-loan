package az.kapitalbank.loan.message.model;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.LeadSource;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.SubProductType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanEvent {
    Long id;
    CustomerEvent customerEvent;
    ProductType productType;
    SubProductType subProductType;
    BigDecimal amount;
    String duration;
    String otherProcessId;
    String couponCode;
    String campaignName;
    LeadSource source;
    String leadComment;
    Boolean mkrAndGovAgreement;
    LocalDate insertedDate;
    FormalizationMethod formalizationMethod;
}
