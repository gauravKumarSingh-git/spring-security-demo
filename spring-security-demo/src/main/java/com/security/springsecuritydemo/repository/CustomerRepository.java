package com.security.springsecuritydemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.springsecuritydemo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByEmail(String email);
    
}
