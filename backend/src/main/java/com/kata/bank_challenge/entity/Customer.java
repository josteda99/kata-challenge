package com.kata.bank_challenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private DocumentTypeEnum documentType;

    private String documentNumber;
    private String fullName;
    private String email;


    public Customer(DocumentTypeEnum documentType,
                    String documentNumber,
                    String email,
                    String fullName) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.email = email;
        this.fullName = fullName;
    }


}