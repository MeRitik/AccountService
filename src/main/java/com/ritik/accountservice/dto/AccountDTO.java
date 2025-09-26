package com.ritik.accountservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
public class AccountDTO {
    @Schema(
            description = "Account Number of the customer",
            example = "1234567890"
    )
    @NotEmpty(message = "Account Number can not be a null or empty")
    @Pattern(regexp = "^$|^[0-9]{10}$", message = "Please provide a valid account number")
    private Long accountNumber;

    @Schema(
            description = "Type of the account",
            example = "Savings"
    )
    @NotEmpty(message = "Account Type can not be a null or empty")
    private String accountType;

    @Schema(
            description = "Address of the branch",
            example = "123 Main Street, City"
    )
    @NotEmpty(message = "Branch Address can not be a null or empty")
    private String branchAddress;
}
