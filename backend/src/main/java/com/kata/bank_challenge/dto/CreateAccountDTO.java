package com.kata.bank_challenge.dto;

import com.kata.bank_challenge.entity.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CreateAccountDTO {
    private UUID customerId;
    private StatusEnum status;
}
