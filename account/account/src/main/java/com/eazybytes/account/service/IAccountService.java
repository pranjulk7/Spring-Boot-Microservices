package com.eazybytes.account.service;

import com.eazybytes.account.dto.AccountsDto;
import com.eazybytes.account.dto.CustomerDto;



public interface IAccountService {
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccountDetails(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);

}
