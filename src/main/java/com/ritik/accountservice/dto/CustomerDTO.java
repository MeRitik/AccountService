package com.ritik.accountservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "Name should be between 5 and 30 characters")
    private String name;

    @NotEmpty(message = "Email can not be a null or empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Pattern(regexp = "^$|^[0-9]{10}$", message = "Please provide a valid mobile number")
    private String mobileNumber;

    private AccountDTO account;
}