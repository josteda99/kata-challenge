package com.kata.bank_challenge.controller;

import com.kata.bank_challenge.dto.CreateAccountDTO;
import com.kata.bank_challenge.entity.Account;
import com.kata.bank_challenge.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private BankService service;

    @PostMapping
    public Account createCustomer(@RequestBody CreateAccountDTO accountDto) {
        return service.createAccount(accountDto);
    }

    @GetMapping
    public List<Account> getAccounts() {
        return service.getAllAccounts();
    }
}
