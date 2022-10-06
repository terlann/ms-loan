package az.kapitalbank.loan.entity;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.Status;
import az.kapitalbank.loan.constants.SubProductType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KB_LEAD_LOAN")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    String id;
    String fullname;
    String pincode;
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    ProductType productType;
    @Enumerated(EnumType.STRING)
    SubProductType subProductType;
    BigDecimal productAmount;
    String productDuration;
    String customerCardPan;
    String customerCif;
    String customerAddress;
    String customerWorkplace;
    String campaignName;
    String couponCode;
    String otherProcessId;
    String leadComment;
    LocalDateTime insertedDate;
    @Enumerated(EnumType.STRING)
    Status status;
    String source;
    Boolean isAgreement;
    FormalizationMethod formalizationMethod;
    BigDecimal monthlyPayment;
    String umicoUserId;
}
