package com.kata.bank_challenge.model;

import com.kata.bank_challenge.dto.CreateAccountDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

@Setter
@Getter
public class Account {
    private String id;
    private Customer customer;
    private String accountNumber;
    private StatusEnum status;

    public Account(String id, Customer customer, String accountNumber, StatusEnum status) {
        this.id = id;
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.status = status;
    }

    public Account(CreateAccountDTO accountDto, Customer customer) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        this.accountNumber = generateRandomAccountNumber(11);
        this.status = accountDto.getStatus();
    }

    public static String generateRandomAccountNumber(int length) {
        Random random = new Random();
        long min = (long) Math.pow(10, length - 1);
        long max = (long) Math.pow(10, length) - 1;
        long accountNumber = min + (long) (random.nextDouble() * (max - min + 1));
        return String.valueOf(accountNumber);
    }
}

