package com.eazybytes.account.service.impl;

import com.eazybytes.account.constants.AccountsConstants;
import com.eazybytes.account.dto.AccountsDto;
import com.eazybytes.account.dto.CustomerDto;
import com.eazybytes.account.entites.Account;
import com.eazybytes.account.entites.Customer;
import com.eazybytes.account.exception.CustomerAlreadyExistsException;
import com.eazybytes.account.exception.ResourceNotFoundException;
import com.eazybytes.account.mapper.AccountsMapper;
import com.eazybytes.account.mapper.CustomerMapper;
import com.eazybytes.account.repositories.AccountRepository;
import com.eazybytes.account.repositories.CustomerRepository;
import com.eazybytes.account.service.IAccountService;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService{



    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber((customerDto.getMobileNumber()));
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }
        Customer savedCustomer=customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));

    }


    /**

     * @return the new account details
     */
    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);


        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Account accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","mobileNumber",mobileNumber)
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated =false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto!=null) {
            Account account = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(() ->
                    new ResourceNotFoundException("Account", "Account Number", accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accountsDto, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(()->
                    new ResourceNotFoundException("Customer","CustomerID",customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto,customer);
            customer = customerRepository.save(customer);
            isUpdated =true;
        }
        return isUpdated;

    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->
               new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
       accountRepository.deleteByCustomerId(customer.getCustomerId());
       customerRepository.deleteById(customer.getCustomerId());
       return true;

    }


}
