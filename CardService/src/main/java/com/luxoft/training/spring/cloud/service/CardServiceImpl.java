package com.luxoft.training.spring.cloud.service;

import com.luxoft.training.spring.cloud.generator.CardNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardNumberGenerator cardNumberGenerator;

    @Override
    public String generateCardNumber() {
        return cardNumberGenerator.generate();
    }
}
