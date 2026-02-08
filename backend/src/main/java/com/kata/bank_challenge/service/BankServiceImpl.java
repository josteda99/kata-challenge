package com.kata.bank_challenge.service;

import com.kata.bank_challenge.dto.*;
import com.kata.bank_challenge.entity.Account;
import com.kata.bank_challenge.entity.Customer;
import com.kata.bank_challenge.repository.AccountRepository;
import com.kata.bank_challenge.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankServiceImpl implements BankService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public BankServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<CustomerWithAccountDto> getAllCustomers() {
        return customerRepository.findAllWithAccount().stream().map(r -> new CustomerWithAccountDto(
                        (Customer) r[0],
                        (Account) r[1]
                ))
                .toList();
    }

    @Override
    public CustomerWithAccountDto createCustomer(CreateCustomerDTO customerDto) {
        Customer customer = new Customer(customerDto.getDocumentType(), customerDto.getDocumentNumber(), customerDto.getEmail(), customerDto.getFullName());
        Customer newCustomer = customerRepository.save(customer);
        return new CustomerWithAccountDto(newCustomer, null);
    }

    @Override
    public CustomerWithAccountDto createAccount(CreateAccountDTO account) {
        Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow();
        Account temporalAccount = new Account(account, customer);
        //check if already has a custoomer linked
        Account newAccount = accountRepository.save(temporalAccount);
        return new CustomerWithAccountDto(customer,newAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(AccountDto::new)
                .toList();
    }

    @Override
    public Optional<AccountDto> getAccountByCustomerId(UUID accountId) {
        return accountRepository.findByCustomerId(accountId)
                .map(AccountDto::new);
    }
}
