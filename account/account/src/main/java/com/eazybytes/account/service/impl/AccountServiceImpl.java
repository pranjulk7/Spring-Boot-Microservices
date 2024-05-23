package com.eazybytes.account.service.impl;

import com.eazybytes.account.constants.AccountsConstants;
import com.eazybytes.account.dto.CustomerDto;
import com.eazybytes.account.entites.Account;
import com.eazybytes.account.entites.Customer;
import com.eazybytes.account.exception.CustomerAlreadyExistsException;
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
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
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
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");;
        return newAccount;
    }


}
