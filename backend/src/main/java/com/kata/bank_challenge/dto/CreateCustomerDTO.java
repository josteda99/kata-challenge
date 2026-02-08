package com.kata.bank_challenge.dto;

import com.kata.bank_challenge.entity.DocumentTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCustomerDTO {
    private DocumentTypeEnum documentType;
    private String documentNumber;
    private String fullName;
    private String email;
}
