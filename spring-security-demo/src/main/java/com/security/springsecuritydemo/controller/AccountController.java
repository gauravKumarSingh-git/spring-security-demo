package com.security.springsecuritydemo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.springsecuritydemo.entity.Account;
import com.security.springsecuritydemo.repository.AccountRepository;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    private AccountRepository accountsRepository;

    @GetMapping("/myAccount")
    public Account getAccountDetails(@RequestParam int id) {
        Account accounts = accountsRepository.findByCustomerId(id);
        if (accounts != null ) {
            return accounts;
        }else {
            return null;
        }
    }

    @PostMapping("/addAccountDetails")
    public ResponseEntity<String> addAccountDetails(@RequestBody Account account){
        try{
            account.setCreateDt(LocalDate.now().toString());
            accountsRepository.save(account);
            return new ResponseEntity<>("Account Detials successfully saved", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}
