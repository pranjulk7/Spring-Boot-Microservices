package com.eazybytes.account.controllers;

import com.eazybytes.account.constants.AccountsConstants;
import com.eazybytes.account.dto.CustomerDto;
import com.eazybytes.account.dto.ErrorResponseDto;
import com.eazybytes.account.dto.ResponseDto;
import com.eazybytes.account.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD Rest APIs for accounts services",
description = "fetch, edit, create, delete accounts")
@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountService iAccountService;

    @Operation(summary = "Create Account Rest API",
    description = "Create accounts")
    @ApiResponse(responseCode = "201",
    description = "Http Status Created")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(summary="Get Accounts",
            description = "Get account details")
   @ApiResponses
    (
    {
        @ApiResponse(responseCode = "200",
                description = "Retrieved account details successfully"),
        @ApiResponse(responseCode = "500",
                description = "Fail to fetch account details",
                content = @Content(
                     schema =   @Schema(
                                implementation = ErrorResponseDto.class
                        )
                )
        )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|^\\d{10}$)", message = "Please Enter a Valid Mobile Number")
                                                           String mobileNumber){
        CustomerDto customerDto = iAccountService.fetchAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "Update account",
    description = "Update Account Details")
    @ApiResponses    (
    {
        @ApiResponse(responseCode = "200",
                description = "Succesfully updated account's details"),
        @ApiResponse(responseCode = "500",
                description = "Some error occured while updating account's details")
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountsDetails(@Valid@RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }


    }
    @Operation(summary = "Delete account",
            description = "Delete Account Details")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Succesfully deleted Account"),
            @ApiResponse(responseCode = "500",
                    description = "Some error occured while deleting account's details")

    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|^\\d{10}$)", message = "Please Enter a Valid Mobile Number")
                                                                String mobileNumber){
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500)
            );
        }
    }

}
