package com.kata.bank_challenge.service;

import com.kata.bank_challenge.dto.CreateAccountDTO;
import com.kata.bank_challenge.dto.CreateCustomerDTO;
import com.kata.bank_challenge.model.Account;
import com.kata.bank_challenge.model.Customer;
import com.kata.bank_challenge.repository.AccountRepository;
import com.kata.bank_challenge.repository.CustomerRepository;

import java.util.List;
import java.util.UUID;

public class BankService {

    private final CustomerRepository customerRepository = new CustomerRepository();
    private final AccountRepository accountRepository = new AccountRepository();

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(CreateCustomerDTO customerDto) {
        Customer customer = new Customer(UUID.randomUUID().toString(), customerDto.getDocumentType(), customerDto.getDocumentNumber(), customerDto.getEmail());
        return customerRepository.save(customer);
    }

    public Account createAccount(CreateAccountDTO accountDto) {
        Customer customer = customerRepository.findById(accountDto.getCustomerId());

        Account account = new Account(accountDto, customer);

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
