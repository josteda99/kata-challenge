package com.kata.bank_challenge.controller;

import com.kata.bank_challenge.dto.CreateCustomerDTO;
import com.kata.bank_challenge.entity.Customer;
import com.kata.bank_challenge.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private BankService service;

    @PostMapping
    public Customer createCustomer(@RequestBody CreateCustomerDTO customer) {
        return service.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return service.getAllCustomers();
    }
}
