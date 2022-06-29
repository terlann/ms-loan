package az.kapitalbank.loan.entity;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.LeadStatus;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.SubProductType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = LeadLoanEntity.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanEntity {
    static final String TABLE_NAME = "LEAD_LOAN";

    @Id
    @SequenceGenerator(name = "gen_seq_lead_loan",
            sequenceName = "seq_lead_loan", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_seq_lead_loan")
    Long id;
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
    LeadStatus status;
    String source;
    Boolean isAgreement;
    FormalizationMethod formalizationMethod;
    BigDecimal monthlyPayment;
    String umicoUserId;
}
