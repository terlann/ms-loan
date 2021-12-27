package az.kapitalbank.loan.message.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.SubProductType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanEvent {
    long id;
    CustomerEvent customer;
    ProductType productType;
    SubProductType subProductType;
    BigDecimal amount;
    int duration;
    String otherProcessId;
    String couponCode;
    String campaignName;
    LeadSource source;
    String leadComment;
    Boolean mkrAndGovAgreement;
    LocalDate insertedDate;
    FormalizationMethod formalizationMethod;
}
