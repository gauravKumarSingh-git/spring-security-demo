package com.security.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CardsController {
    @GetMapping("/myCards")
    public String getCardsDetails(){
        return "Card Details returned from DB";
    }
}
