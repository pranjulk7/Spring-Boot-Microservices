package com.eazybytes.account.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "can't be Empty")
    @Pattern(regexp = "(^$|^\\d{15}$)",message = "account Number should be 15 numbers")
    private Long accountNumber;

    @NotEmpty(message = "Account Type can't be Empty")
    private String accountType;

    @NotEmpty(message = "Branch Address can't be Empty")
    private String branchAddress;

}
