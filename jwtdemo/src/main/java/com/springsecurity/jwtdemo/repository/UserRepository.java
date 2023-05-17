package com.springsecurity.jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.jwtdemo.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    
}
