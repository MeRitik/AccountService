package com.ritik.accountservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDTO {
    @NotEmpty(message = "Account Number can not be a null or empty")
    @Pattern(regexp = "^$|^[0-9]{10}$", message = "Please provide a valid account number")
    private Long accountNumber;

    @NotEmpty(message = "Account Type can not be a null or empty")
    private String accountType;

    @NotEmpty(message = "Branch Address can not be a null or empty")
    private String branchAddress;
}
