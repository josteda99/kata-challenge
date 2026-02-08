package com.kata.bank_challenge.service;

import com.kata.bank_challenge.dto.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankService {
    List<CustomerWithAccountDto> getAllCustomers();

    CustomerWithAccountDto createCustomer(CreateCustomerDTO customer);

    CustomerWithAccountDto createAccount(CreateAccountDTO account);

    List<AccountDto> getAllAccounts();

    Optional<AccountDto> getAccountByCustomerId(UUID accountId);
}
