package com.security.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LoansController {

    @GetMapping("/myLoans")
    public String getLoansDetails(){
        return "Loan details returned from DB";
    }
}
