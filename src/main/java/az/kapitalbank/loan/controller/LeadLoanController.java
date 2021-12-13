package az.kapitalbank.loan.controller;

import az.kapitalbank.loan.dto.LeadLoanRequestDto;
import az.kapitalbank.loan.dto.response.WrapperResponse;
import az.kapitalbank.loan.service.LeadLoanService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/lead/loan")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanController {
    final LeadLoanService leadLoanService;

    @PostMapping
    public ResponseEntity<?> addLead(@Valid @RequestBody LeadLoanRequestDto request, @RequestHeader("X-lEAD-SOURCE") String source) {
        WrapperResponse<?> wrapperResponse = leadLoanService.saveLead(request, source);
        return ResponseEntity.ok(wrapperResponse);
    }

}
