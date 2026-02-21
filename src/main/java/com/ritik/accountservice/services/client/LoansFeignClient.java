package com.ritik.accountservice.services.client;

import com.ritik.accountservice.dto.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans", fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<LoanDTO> fetchLoanDetails(
            @RequestHeader(value = "bank-correlation-id") String correlationId,
            @RequestParam(value = "mobileNumber") String mobileNumber);

}