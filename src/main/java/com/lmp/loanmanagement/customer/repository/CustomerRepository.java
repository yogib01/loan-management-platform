package com.lmp.loanmanagement.customer.repository;

import com.lmp.loanmanagement.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByPanNumber(String panNumber);

    Optional<Customer> findByAadhaarNumber(String aadhaarNumber);
}
