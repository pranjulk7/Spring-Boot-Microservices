package com.eazybytes.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        description = "Schema To hold Accounts Information",
        name = "Accounts"
)
@Data
public class AccountsDto {

    @Schema(
            description = "Account Number",
            example ="1234234512345"
    )
    @NotEmpty(message = "Account Number can't be Empty")
    @Pattern(regexp = "(^$|^\\d{15}$)",message = "Account Number should be 15 numbers")
    private Long accountNumber;

    @Schema(
            description = "Type of account",
            example = "Current"
    )
    @NotEmpty(message = "Account Type can't be Empty")
    private String accountType;

    @Schema(
            description = "Branch address of user",
            example = "123, Villa no.20, This Road , Some Address"
    )
    @NotEmpty(message = "Branch Address can't be Empty")
    private String branchAddress;

}
