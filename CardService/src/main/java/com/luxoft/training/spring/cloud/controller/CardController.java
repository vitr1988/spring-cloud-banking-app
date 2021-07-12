package com.luxoft.training.spring.cloud.controller;

import com.luxoft.training.spring.cloud.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PreAuthorize("hasAuthority('CARD_WRITE')")
    @GetMapping("/create")
    String generateCardNumber() {
        return cardService.generateCardNumber();
    }
}
