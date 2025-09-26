package com.ritik.accountservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDTO {
    @Schema(
            description = "Name of the customer",
            example = "John Doe"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "Name should be between 5 and 30 characters")
    private String name;

    @Schema(
            description = "Email address of the customer",
            example = "johndoe@example.com"
    )
    @NotEmpty(message = "Email can not be a null or empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "9934567890"
    )
    @Pattern(regexp = "^$|^[0-9]{10}$", message = "Please provide a valid mobile number")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountDTO account;
}