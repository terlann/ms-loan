package az.kapitalbank.loan.dto;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.SubProductType;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanRequestDto {

    @NotNull
    String phoneNumber;
    @Size(min = 7, max = 7)
    String pincode;
    String fullname;
    String cif;
    String address;
    String cardPan;
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
    BigDecimal monthlyPayment;
    String umicoUserId;
}
