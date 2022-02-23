package az.kapitalbank.loan.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.SubProductType;
import az.kapitalbank.loan.constraint.Phone;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanRequestDto {

    @Phone
    @NotNull
    String phoneNumber;
    @Size(min = 7, max = 7)
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
    @NotNull
    BigDecimal productAmount;
    Integer productDuration;
    ProductType productType;
    SubProductType subProductType;
    FormalizationMethod formalizationMethod;
}
