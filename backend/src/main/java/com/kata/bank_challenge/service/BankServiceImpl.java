package com.kata.bank_challenge.service;

import com.kata.bank_challenge.dto.CreateAccountDTO;
import com.kata.bank_challenge.dto.CreateCustomerDTO;
import com.kata.bank_challenge.entity.Account;
import com.kata.bank_challenge.entity.Customer;
import com.kata.bank_challenge.repository.AccountRepository;
import com.kata.bank_challenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(CreateCustomerDTO customerDto) {
        Customer customer = new Customer(customerDto.getDocumentType(), customerDto.getDocumentNumber(), customerDto.getEmail());
        return customerRepository.save(customer);
    }

    @Override
    public Account createAccount(CreateAccountDTO accountDto) {
        Customer customer = customerRepository.findById(accountDto.getCustomerId()).orElseThrow();
        Account account = new Account(accountDto, customer);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

}
