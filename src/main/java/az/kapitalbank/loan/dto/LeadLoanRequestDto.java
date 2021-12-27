package az.kapitalbank.loan.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.SubProductType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;

import static az.kapitalbank.loan.constants.ProductType.NONE;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanRequestDto {
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
