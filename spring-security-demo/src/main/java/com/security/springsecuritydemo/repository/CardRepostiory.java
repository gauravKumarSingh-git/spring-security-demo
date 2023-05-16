package com.security.springsecuritydemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.springsecuritydemo.entity.Card;

public interface CardRepostiory extends JpaRepository<Card, Long> {
    List<Card> findByCustomerId(int customerId);
}
