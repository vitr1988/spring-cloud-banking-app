package com.luxoft.training.spring.cloud.service;

import com.luxoft.training.spring.cloud.dto.ProcessingDto;

import java.math.BigDecimal;

public interface ProcessingService {
    ProcessingDto issueCard(Long accountId);

    void spendMoney(String cardNumber, BigDecimal sum);

    ProcessingDto getProcessing(Long accountId);
}
