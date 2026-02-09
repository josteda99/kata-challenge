package com.kata.bank_challenge.controller;

import com.kata.bank_challenge.dto.CreateCustomerDTO;
import com.kata.bank_challenge.dto.CustomerWithAccountDto;
import com.kata.bank_challenge.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private BankService service;

    @PostMapping
    public ResponseEntity<CustomerWithAccountDto> createCustomer(@RequestBody CreateCustomerDTO customer) {
        return service.createCustomer(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerWithAccountDto>> getCustomers() {
        return service.getAllCustomers();
    }
}
