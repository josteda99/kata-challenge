package com.kata.bank_challenge.repository;

import com.kata.bank_challenge.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Query("""
            SELECT c, a
            FROM Customer c 
            LEFT JOIN Account a ON a.customer.id = c.id
            """)
    List<Object[]> findAllWithAccount();

    Optional<Customer> findByDocumentNumber(String documentNumber);
}
