package com.security.springsecuritydemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.springsecuritydemo.entity.Card;
import com.security.springsecuritydemo.repository.CardRepostiory;

@RestController
@RequestMapping
public class CardsController {
    @Autowired
    private CardRepostiory cardsRepository;

    @GetMapping("/myCards")
    public List<Card> getCardDetails(@RequestParam int id) {
        List<Card> cards = cardsRepository.findByCustomerId(id);
        if (cards != null ) {
            return cards;
        }else {
            return null;
        }
    }
}
