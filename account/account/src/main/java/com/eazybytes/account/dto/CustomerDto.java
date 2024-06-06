package com.eazybytes.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account Information"
)
public class CustomerDto {

    @Schema(
            description = "Name of customer", example="vyas"
    )
    @NotEmpty(message = "Name cannot be null or Empty")
    @Size(min = 5, max=30, message="The length of the customer name should be berween 5 and 30")
    private String name;

    @Schema(
            description = "Email of customer", example="kjsadhf@com.com"
    )
    @NotEmpty(message = "Email cannot be null or Empty")
    @Email(message = "email should be valid")
    private String email;

    @Schema(
            description = "Mobile number of customer", example="9557949966"
    )
   @Pattern(regexp = "(^$|^\\d{10}$)", message = "mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
