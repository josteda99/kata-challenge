package com.kata.bank_challenge.repository;

import com.kata.bank_challenge.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();

    public List<Customer> findAll() {
        return customers;
    }

    public Customer save(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public Customer findById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }
}
