package com.kata.bank_challenge.dto;

import com.kata.bank_challenge.entity.Account;
import com.kata.bank_challenge.entity.Customer;
import com.kata.bank_challenge.entity.DocumentTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
public class CustomerWithAccountDto {
    private UUID id;
    private DocumentTypeEnum documentType;
    private String documentNumber;
    private String fullName;
    private String email;
    private boolean hasAccount;
    private AccountDto account;


    public CustomerWithAccountDto(Customer customer, Account account) {
        this.id = customer.getId();
        this.documentType = customer.getDocumentType();
        this.fullName = customer.getFullName();
        this.documentNumber = customer.getDocumentNumber();
        this.email = customer.getEmail();
        this.hasAccount = (account != null);
        this.account = account != null ? new AccountDto(account) : null;
    }
}
