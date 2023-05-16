package com.security.springsecuritydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.springsecuritydemo.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByCustomerId(int customerId);
}
