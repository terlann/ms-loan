package az.kapitalbank.loan.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.SubProductType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanRequestDto {
    @NotNull
    String phoneNumber;
    String pincode;
    String fullname;
    String cif;
    String address;
    Integer cardPan;
    String workplace;
    String couponCode;
    String leadComment;
    String campaignName;
    Boolean isAgreement;
    String otherProcessId;
    BigDecimal productAmount;
    Integer productDuration;
    ProductType productType;
    SubProductType subProductType;
    FormalizationMethod formalizationMethod;
}
