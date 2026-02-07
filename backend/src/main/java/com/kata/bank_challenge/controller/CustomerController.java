package com.kata.bank_challenge.controller;

import com.kata.bank_challenge.dto.CreateCustomerDTO;
import com.kata.bank_challenge.entity.Customer;
import com.kata.bank_challenge.service.BankServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final BankServiceImpl service;

    public CustomerController(BankServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CreateCustomerDTO customer) {
        return service.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return service.getAllCustomers();
    }
}
