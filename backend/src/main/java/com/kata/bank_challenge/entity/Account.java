package com.kata.bank_challenge.entity;

import com.kata.bank_challenge.dto.CreateAccountDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Random;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "customer_id",unique = true)
    private Customer customer;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    public Account(CreateAccountDTO accountDto, Customer customer) {
        this.customer = customer;
        this.accountNumber = generateRandomAccountNumber(10);
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

