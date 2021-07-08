package com.luxoft.training.spring.cloud.controller;

import com.luxoft.training.spring.cloud.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/create")
    String generateCardNumber() {
        return cardService.generateCardNumber();
    }
}
