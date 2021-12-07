package az.kapitalbank.loan.mapper;

import az.kapitalbank.loan.constants.LeadSource;
import az.kapitalbank.loan.constants.ProductType;
import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.message.model.CustomerEvent;
import az.kapitalbank.loan.message.model.LeadLoanEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;


@ExtendWith(MockitoExtension.class)
class LeadLoanEventMapperTest {

    @Test
    void toLoanEntity() {
        LeadLoanRequestDto leadLoanRequestDtoMock = new LeadLoanRequestDto();
        leadLoanRequestDtoMock.setFullname("Irshad Eyvazov");
        leadLoanRequestDtoMock.setPincode("2BDS9TM");
        leadLoanRequestDtoMock.setProductType(ProductType.CASH_LOAN);
        leadLoanRequestDtoMock.setProductAmount(new BigDecimal("1000"));

        LeadLoanEntity leadLoanEntityActual = new LeadLoanEntity();
        leadLoanEntityActual.setFullname("Irshad Eyvazov");
        leadLoanEntityActual.setPincode("2BDS9TM");
        leadLoanEntityActual.setProductType(ProductType.CASH_LOAN);
        leadLoanEntityActual.setProductAmount(new BigDecimal("1000"));
        leadLoanEntityActual.setSource(LeadSource.KAPITALBANK);

        LeadLoanEntity leadLoanEntityResult = LeadLoanMapper.INSTANCE.toLoanEntity(leadLoanRequestDtoMock, LeadSource.KAPITALBANK);

        Assertions.assertThat(leadLoanEntityActual).usingRecursiveComparison().isEqualTo(leadLoanEntityResult);

    }

    @Test
    void toLeadLoanModel() {
        LeadLoanEntity leadLoanEntityMock = new LeadLoanEntity();
        leadLoanEntityMock.setFullname("Irshad Eyvazov");
        leadLoanEntityMock.setPincode("2BDS9TM");
        leadLoanEntityMock.setProductType(ProductType.CASH_LOAN);
        leadLoanEntityMock.setProductAmount(new BigDecimal("1000"));
        leadLoanEntityMock.setSource(LeadSource.KAPITALBANK);

        LeadLoanEvent leadLoanEventActual = LeadLoanEvent.builder()
                .id(1000L)
                .customerEvent(CustomerEvent.builder()
                        .fullname("Irshad Eyvazov")
                        .pincode("2BDS9TM")
                        .build())
                .productType(ProductType.CASH_LOAN)
                .source(LeadSource.KAPITALBANK)
                .build();

        LeadLoanEvent leadLoanEventResult = LeadLoanMapper.INSTANCE.toLeadLoanModel(leadLoanEntityMock);

        Assertions.assertThat(leadLoanEventActual).usingRecursiveComparison().isEqualTo(leadLoanEventResult);
    }
}