package az.kapitalbank.loan.controller;

import static az.kapitalbank.loan.constants.TestConstant.LEAD_ID;
import static az.kapitalbank.loan.constants.TestConstant.LEAD_SOURCE;
import static az.kapitalbank.loan.constants.TestConstant.PHONE_NUMBER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.dto.response.LeadResponseDto;
import az.kapitalbank.loan.dto.response.WrapperResponse;
import az.kapitalbank.loan.service.LeadLoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("local")
@WebMvcTest(LeadLoanController.class)
class LeadLoanControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    LeadLoanService leadLoanService;

    @Test
    void addLead_xLeadSourcePresentsInHeader_ShouldResponseOkWithLeadId() throws Exception {
        var request = LeadLoanRequestDto.builder()
                .phoneNumber(PHONE_NUMBER)
                .build();
        var response = new LeadResponseDto(LEAD_ID);
        when(leadLoanService.saveLead(request, LEAD_SOURCE)).thenReturn(
                WrapperResponse.<LeadResponseDto>builder().data(response).build());
        mockMvc.perform(post("/v1/lead/loan")
                        .header("X-lEAD-SOURCE", LEAD_SOURCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(jsonPath("$.data.lead_id").value(response.getLeadId()))
                .andExpect(status().isOk());

        verify(leadLoanService).saveLead(request, LEAD_SOURCE);
    }

    @Test
    void addLead_PhoneNumberIsNull_ShouldResponseBadRequest() throws Exception {
        var request = LeadLoanRequestDto.builder()
                .build();
        mockMvc.perform(post("/v1/lead/loan")
                        .header("X-lEAD-SOURCE", LEAD_SOURCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}

