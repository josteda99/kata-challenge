package com.kata.bank_challenge.dto;

import com.kata.bank_challenge.model.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAccountDTO {
    private String customerId;
    private StatusEnum status;
}
