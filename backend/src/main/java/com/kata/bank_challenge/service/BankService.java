package com.kata.bank_challenge.service;

import com.kata.bank_challenge.dto.CreateAccountDTO;
import com.kata.bank_challenge.dto.CreateCustomerDTO;
import com.kata.bank_challenge.entity.Account;
import com.kata.bank_challenge.entity.Customer;

import java.util.List;

public interface BankService {
    List<Customer> getAllCustomers();

    Customer createCustomer(CreateCustomerDTO customerDto);

    Account createAccount(CreateAccountDTO accountDto);

    List<Account> getAllAccounts();
}
