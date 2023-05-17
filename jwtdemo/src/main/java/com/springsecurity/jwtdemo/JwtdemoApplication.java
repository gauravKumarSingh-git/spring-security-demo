package com.springsecurity.jwtdemo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springsecurity.jwtdemo.entity.Users;
import com.springsecurity.jwtdemo.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class JwtdemoApplication {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void initUsers(){
		List<Users> users = Stream.of(
			new Users(101, "gaurav", "password", "gaurav@gmail.com"),
			new Users(102, "user2", "pass2", "user@gmial.com")
		).collect(Collectors.toList());
		userRepository.saveAll(users);
	}


	public static void main(String[] args) {
		SpringApplication.run(JwtdemoApplication.class, args);
	}

}
