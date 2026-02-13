package com.ritik.accountservice.services.client;

import com.ritik.accountservice.dto.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<CardDTO> fetchCardDetails(@RequestParam(value = "mobileNumber") String mobileNumber);

}
