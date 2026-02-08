package com.kata.bank_challenge.dto;

import com.kata.bank_challenge.entity.Account;
import com.kata.bank_challenge.entity.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
public class AccountDto {
    private UUID id;
    private CustomerDto customer;
    private String accountNumber;
    private StatusEnum status;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.customer = new CustomerDto(account.getCustomer());
        this.accountNumber = account.getAccountNumber();
        this.status = account.getStatus();
    }

}
