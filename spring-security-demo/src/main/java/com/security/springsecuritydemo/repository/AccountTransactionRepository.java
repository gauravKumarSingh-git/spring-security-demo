package com.security.springsecuritydemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.springsecuritydemo.entity.AccountTransaction;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Integer> {
    List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(int customerId);   
}
