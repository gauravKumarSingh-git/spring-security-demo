package com.security.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BalanceController {
    
    @GetMapping("/myBalance")
    public String getBalanceDetails(){
        return "Balance detials returned from DB";
    }
}
