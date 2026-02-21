package com.ritik.accountservice.services.client;

import com.ritik.accountservice.dto.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards", fallback = CardsFallback.class)
public interface CardsFeignClient {
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<CardDTO> fetchCardDetails(
            @RequestHeader(value = "bank-correlation-id") String correlationId,
            @RequestParam(value = "mobileNumber") String mobileNumber);

}
