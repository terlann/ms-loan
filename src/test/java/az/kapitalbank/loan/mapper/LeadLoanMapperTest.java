package az.kapitalbank.loan.mapper;

import static az.kapitalbank.loan.constants.ConstantObject.getLeadLoanEntity;
import static az.kapitalbank.loan.constants.ConstantObject.getLeadLoanEntityForOptimus;
import static az.kapitalbank.loan.constants.ConstantObject.getLeadLoanEventForTeleSales;
import static az.kapitalbank.loan.constants.ConstantObject.getLeadLoanRequestDto;
import static az.kapitalbank.loan.constants.ConstantObject.getLeadLoanRequestDtoForTelesales;
import static az.kapitalbank.loan.constants.ConstantObject.getLeadSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import az.kapitalbank.loan.constants.FormalizationMethod;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.constants.Status;
import az.kapitalbank.loan.constants.SubProductType;
import az.kapitalbank.loan.dto.LeadStatusDto;
import az.kapitalbank.loan.message.optimus.model.CustomerEvent;
import az.kapitalbank.loan.message.optimus.model.LeadLoanEvent;
import az.kapitalbank.loan.message.optimus.model.LeadSource;
import az.kapitalbank.loan.message.optimus.model.LeadStatusEvent;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@SpringBootTest(classes = LeadLoanMapperImpl.class)
class LeadLoanMapperTest {
    @Autowired
    LeadLoanMapper leadLoanMapper;

    @Test
    void toLoanEntity() {
        var leadSource = getLeadSource();
        var leadLoanRequestDto = getLeadLoanRequestDto();

        var expected = getLeadLoanEntity(leadLoanRequestDto, leadSource);

        var actual = leadLoanMapper.toLoanEntity(leadLoanRequestDto, leadSource);
        assertEquals(expected, actual);
    }

    @Test
    void toLeadLoanModel() {
        var leadLoanEntity = getLeadLoanEntityForOptimus(getLeadSource());
        var leadLoanEvent = LeadLoanEvent.builder()
                .customer(CustomerEvent.builder()
                        .cif("0130179")
                        .pan("4169111122223333").pin("5JR9R11")
                        .address("Baki.s/Nizami.k")
                        .fullName("Terlan Qurbanmov")
                        .workplace("KapitalBank")
                        .phoneNumber("+994773227040")
                        .build())
                .productType(ProductType.UMICO_MARKETPLACE)
                .campaignName("KapitalBank")
                .subProductType(SubProductType.UMICO)
                .amount(BigDecimal.ONE)
                .duration(6)
                .source(LeadSource.builder()
                        .name(getLeadSource().getName())
                        .code(getLeadSource().getCode())
                        .build())
                .comment("no comment")
                .mkrAndGovAgreement(true)
                .monthlyPayment(BigDecimal.ONE)
                .formalizationMethod(FormalizationMethod.VIDEO_CALL)
                .otherProcessId("5612")
                .fraudReason("PIN")
                .build();
        var actual = leadLoanMapper.toLeadLoanModel(leadLoanEntity, getLeadSource());
        assertEquals(leadLoanEvent, actual);
    }

    @Test
    void toLeadLoanRequestDto() {
        var leadLoanEvent = getLeadLoanEventForTeleSales();
        var expected = getLeadLoanRequestDtoForTelesales();
        var actual = leadLoanMapper.toLeadLoanRequestDto(leadLoanEvent);
        assertEquals(expected, actual);
    }

    @Test
    void toLeadStatusDto() {
        var leadStatusDto = LeadStatusDto.builder()
                .id("0001")
                .status(Status.WAITING)
                .build();
        var leadStatus = LeadStatusEvent.builder()
                .id("0001")
                .status(Status.WAITING)
                .build();
        var exptected = leadStatusDto;
        var actual = leadLoanMapper.toLeadStatusDto(leadStatus);
        assertEquals(exptected, actual);
    }
}
