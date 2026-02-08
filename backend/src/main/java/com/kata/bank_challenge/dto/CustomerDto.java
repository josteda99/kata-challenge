package com.kata.bank_challenge.dto;

import com.kata.bank_challenge.entity.Account;
import com.kata.bank_challenge.entity.Customer;
import com.kata.bank_challenge.entity.DocumentTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CustomerDto {
    private UUID id;
    private DocumentTypeEnum documentType;
    private String documentNumber;
    private String fullName;
    private String email;


    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.documentType = customer.getDocumentType();
        this.fullName = customer.getFullName();
        this.documentNumber = customer.getDocumentNumber();
        this.email = customer.getEmail();

    }
}
