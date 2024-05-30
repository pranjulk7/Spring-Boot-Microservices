package com.eazybytes.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or Empty")
    private String name;

    @NotEmpty(message = "Email cannot be null or Empty")
    @Email(message = "email should be valid")
    private String email;

   @Pattern(regexp = "(^$|^\\d{10}$)", message = "mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
