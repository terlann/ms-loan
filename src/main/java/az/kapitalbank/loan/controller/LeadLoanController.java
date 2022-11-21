package az.kapitalbank.loan.controller;

import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.dto.response.LeadResponseDto;
import az.kapitalbank.loan.dto.response.WrapperResponse;
import az.kapitalbank.loan.service.LeadLoanService;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/lead/loan")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanController {
    final LeadLoanService leadLoanService;

    @PostMapping
    public ResponseEntity<WrapperResponse<LeadResponseDto>> addLead(
            @Valid @RequestBody LeadLoanRequestDto request,
            @RequestHeader("X-lEAD-SOURCE") String source) {
        WrapperResponse<LeadResponseDto> wrapperResponse =
                leadLoanService.saveLead(request, source);
        return ResponseEntity.ok(wrapperResponse);
    }

}
