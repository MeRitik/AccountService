package com.ritik.accountservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer, Accounts, Cards and Loans Details"
)
public class CustomerDetailsDto {
    @Schema(description = "Name of the customer", example = "Ritik Ranjan")
    private String name;
    @Schema(description = "Email address of the customer", example = "ritik@bank.com")
    private String email;
    @Schema(description = "Mobile number of the customer", example = "9934567890")
    private String mobileNumber;
    @Schema(description = "Account details of the customer")
    private AccountDTO account;
    @Schema(description = "Loan details of the customer")
    private LoanDTO loan;
    @Schema(description = "Card details of the customer")
    private CardDTO card;
}
