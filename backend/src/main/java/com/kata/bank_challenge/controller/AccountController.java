package com.kata.bank_challenge.controller;

import com.kata.bank_challenge.dto.CreateAccountDTO;
import com.kata.bank_challenge.model.Account;
import com.kata.bank_challenge.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final BankService service = new BankService();

    @PostMapping
    public Account createCustomer(@RequestBody CreateAccountDTO accountDto) {
        return service.createAccount(accountDto);
    }

    @GetMapping
    public List<Account> getAccounts() {
        return service.getAllAccounts();
    }
}
