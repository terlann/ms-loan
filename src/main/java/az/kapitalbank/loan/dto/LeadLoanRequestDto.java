package az.kapitalbank.loan.dto;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.SubProductType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanRequestDto {
    @NotNull
    String fullname;
    @NotNull
    String phoneNumber;
    String pincode;
    String cif;
    String cardPan;
    String workplace;
    String address;
    ProductType productType;
    SubProductType subProductType;
    FormalizationMethod formalizationMethod;
    BigDecimal productAmount;
    String productDuration;
    String otherProcessId;
    String couponCode;
    String campaignName;
    String leadComment;
    Boolean isAgreement;
}
