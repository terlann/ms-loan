package az.kapitalbank.loan.message.optimus.model;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.SubProductType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanEvent {
    String id;
    CustomerEvent customer;
    ProductType productType;
    SubProductType subProductType;
    BigDecimal amount;
    Integer duration;
    String otherProcessId;
    String couponCode;
    String campaignName;
    LeadSource source;
    String leadComment;
    Boolean mkrAndGovAgreement;
    LocalDateTime insertedDate;
    FormalizationMethod formalizationMethod;
    BigDecimal monthlyPayment;
    String umicoUserId;
}
