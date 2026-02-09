package com.kata.bank_challenge.controller;

import com.kata.bank_challenge.dto.AccountDto;
import com.kata.bank_challenge.dto.CreateAccountDTO;
import com.kata.bank_challenge.dto.CustomerWithAccountDto;
import com.kata.bank_challenge.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private BankService service;

    @PostMapping
    public ResponseEntity<CustomerWithAccountDto> createCustomer(@RequestBody CreateAccountDTO accountDto) {
        return service.createAccount(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return service.getAllAccounts();
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Optional<AccountDto>> getAccountByCustomerId(
            @PathVariable UUID customerId) {
        return service.getAccountByCustomerId(customerId);
    }
}
