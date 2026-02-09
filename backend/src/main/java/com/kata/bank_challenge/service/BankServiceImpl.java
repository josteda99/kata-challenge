package com.kata.bank_challenge.service;

import com.kata.bank_challenge.dto.*;
import com.kata.bank_challenge.entity.Account;
import com.kata.bank_challenge.entity.Customer;
import com.kata.bank_challenge.repository.AccountRepository;
import com.kata.bank_challenge.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CustomerWithAccountDto>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerRepository.findAllWithAccount().stream().map(r -> new CustomerWithAccountDto(
                        (Customer) r[0],
                        (Account) r[1]
                ))
                .toList());
    }

    @Override
    public ResponseEntity<CustomerWithAccountDto> createCustomer(CreateCustomerDTO customerDto) {
        Customer checkedCustomer = customerRepository.findByDocumentNumber(customerDto.getDocumentNumber()).orElse(null);

        if (checkedCustomer != null) return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Customer customer = new Customer(customerDto.getDocumentType(), customerDto.getDocumentNumber(), customerDto.getEmail(), customerDto.getFullName());
        Customer newCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerWithAccountDto(newCustomer, null));
    }

    @Override
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountRepository.findAll().stream()
                .map(AccountDto::new)
                .toList());
    }

    @Override
    public ResponseEntity<CustomerWithAccountDto> createAccount(CreateAccountDTO account) {
        Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow();
        Account checkedAccount = accountRepository.findByCustomerId(account.getCustomerId()).orElse(null);

        if (checkedAccount != null) return ResponseEntity.status(HttpStatus.CONFLICT).build();;

        Account temporalAccount = new Account(account, customer);
        Account newAccount = accountRepository.save(temporalAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerWithAccountDto(customer, newAccount));
    }

    @Override
    public ResponseEntity<Optional<AccountDto>> getAccountByCustomerId(UUID accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(accountRepository.findByCustomerId(accountId)
                .map(AccountDto::new));
    }
}
