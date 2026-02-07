package com.kata.bank_challenge.model;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Customer {
    private String id;
    private DocumentTypeEnum documentType;
    private String documentNumber;
    private String email;

    public Customer(String id, DocumentTypeEnum documentType, String documentNumber, String email) {
        this.id = id;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.email = email;
    }


}
