package com.ritik.accountservice.controller;

import com.ritik.accountservice.dto.CustomerDetailsDto;
import com.ritik.accountservice.dto.ErrorResponseDTO;
import com.ritik.accountservice.services.ICustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(
        name = "CRUD REST APIs for Customers in Bank",
        description = "REST APIs in Bank to FETCH customer details"
)
@RequiredArgsConstructor
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final ICustomersService customersService;

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch customer details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = CustomerDetailsDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("bank-correlation-id") String correlationId,
                                                                   @RequestParam
                                                                   @Pattern(regexp = "^|[0-9]{10}", message = "Mobile number must be 10 digits")
                                                                   String mobileNumber) {
        log.debug("bank-correlation-id found: {}", correlationId);
        log.trace("Customer Controller");
        CustomerDetailsDto dto = customersService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.ok(dto);
    }
}
