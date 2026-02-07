package com.kata.bank_challenge.repository;

import com.kata.bank_challenge.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private List<Account> accounts = new ArrayList<>();

    public List<Account> findAll() {
        return accounts;
    }

    public Account save(Account account) {
        accounts.add(account);
        return account;
    }
}
