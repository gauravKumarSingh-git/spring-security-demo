package com.security.springsecuritydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.springsecuritydemo.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    
}
