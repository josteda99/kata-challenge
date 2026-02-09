package com.kata.bank_challenge.service;

import com.kata.bank_challenge.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankService {
    ResponseEntity<List<CustomerWithAccountDto>> getAllCustomers();

    ResponseEntity<CustomerWithAccountDto> createCustomer(CreateCustomerDTO customer);

    ResponseEntity<CustomerWithAccountDto> createAccount(CreateAccountDTO account);

    ResponseEntity<List<AccountDto>> getAllAccounts();

    ResponseEntity<Optional<AccountDto>> getAccountByCustomerId(UUID accountId);
}
