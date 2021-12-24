package az.kapitalbank.loan.service;

import az.kapitalbank.loan.mapper.LeadLoanMapper;
import az.kapitalbank.loan.message.LeadLoanSender;
import az.kapitalbank.loan.message.model.LeadLoanEvent;
import az.kapitalbank.loan.repository.LeadLoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LeadLoanEventServiceTest {

    @Mock
    LeadLoanMapper leadLoanMapper;
    @Mock
    LeadLoanRepository leadLoanRepository;
    @Mock
    LeadLoanSender leadLoanSender;

    @InjectMocks
    LeadLoanService leadLoanService;

    LeadLoanEvent leadLoanEventMock;


    @BeforeEach
    void setUp() {
/*        leadLoanEventMock = LeadLoanEvent.builder()
                .id(1000L)
                .customerEvent(CustomerEvent.builder()
                        .fullname("Irshad Eyvazov")
                        .pincode("2BDS9TM")
                        .build())
                .productType(ProductType.CASH_LOAN)
                .source("001")
                .build();*/
    }

    @Test
    void whenSaveLeadCall_ThenShouldBeSuccess() {
//        LeadLoanRequestDto leadLoanRequestDtoMock = new LeadLoanRequestDto();
//        leadLoanRequestDtoMock.setFullname("Irshad Eyvazov");
//        leadLoanRequestDtoMock.setPincode("2BDS9TM");
//        leadLoanRequestDtoMock.setProductType(ProductType.CASH_LOAN);
//
//        SaveLeadResponseDto saveLeadResponseDto = new SaveLeadResponseDto();
//        saveLeadResponseDto.setLeadId("1000");
//        WrapperResponse wrapperResponse = WrapperResponse.builder()
//                .data(saveLeadResponseDto)
//                .build();
//
//        LeadLoanEntity leadLoanEntityRequestMock = new LeadLoanEntity();
//        leadLoanEntityRequestMock.setFullname("Irshad Eyvazov");
//        leadLoanEntityRequestMock.setPincode("2BDS9TM");
//        leadLoanEntityRequestMock.setProductType(ProductType.CASH_LOAN);
//        leadLoanEntityRequestMock.setSource(LeadSource.KAPITALBANK);
//        leadLoanEntityRequestMock.setStatus(LeadStatus.WAITING);
//
//        LeadLoanEntity leadLoanEntityResponseMock = leadLoanEntityRequestMock;
//        leadLoanEntityRequestMock.setId(1000L);
//
//        Mockito.when(leadLoanMapper.toLoanEntity(leadLoanRequestDtoMock, LeadSource.KAPITALBANK))
//                                              .thenReturn(leadLoanEntityResponseMock);
//        Mockito.when(leadLoanRepository.save(leadLoanEntityRequestMock)).thenReturn(leadLoanEntityResponseMock);
//
//        LeadLoanEntity leadLoanEntityMappingResult = leadLoanMapper.toLoanEntity(leadLoanRequestDtoMock,
//                                                                                 LeadSource.KAPITALBANK);
//
//        LeadLoanEntity leadLoanEntityAfterSaveResult = leadLoanRepository.save(leadLoanEntityMappingResult);
//
//        Mockito.when(leadLoanMapper.toLeadLoanModel(leadLoanEntityAfterSaveResult)).thenReturn(leadLoanEventMock);
//
//        LeadLoanEvent leadLoanEventResult = leadLoanMapper.toLeadLoanModel(leadLoanEntityAfterSaveResult);
//
//        //Mockito.when(leadLoanService.saveLead(leadLoanRequestDtoMock, LeadSource.KAPITALBANK))
//                                                                    .thenReturn(wrapperResponse);
//
//        Assertions.assertThat(LeadSource.KAPITALBANK).isEqualTo(leadLoanEntityMappingResult.getSource());
//        Assertions.assertThat(1000L).isEqualTo(leadLoanEntityAfterSaveResult.getId());
//        Assertions.assertThat(leadLoanEntityAfterSaveResult.getFullname())
//            .isEqualTo(leadLoanEventResult.getCustomerEvent().getFullname());
//        //Assertions.assertThat(wrapperResponse.getData()).isEqualTo(saveLeadResponseDto);
    }

    @Test
    void sendLeadWithMessaging() {
       /* Mockito.doNothing().when(leadLoanSender).sendMessage(leadLoanEventMock);
        Mockito.verify(leadLoanSender, Mockito.times(1)).sendMessage(leadLoanEventMock);*/
    }

}
