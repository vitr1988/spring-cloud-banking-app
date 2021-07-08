package com.luxoft.training.spring.cloud.service;

import com.luxoft.training.spring.cloud.domain.ProcessingEntity;
import com.luxoft.training.spring.cloud.dto.ProcessingDto;
import com.luxoft.training.spring.cloud.feign.AccountServiceClient;
import com.luxoft.training.spring.cloud.feign.CardServiceClient;
import com.luxoft.training.spring.cloud.mapper.ProcessingMapper;
import com.luxoft.training.spring.cloud.repository.ProcessingRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private final ProcessingRepository processingRepository;
    private final AccountServiceClient accountServiceClient;
    private final CardServiceClient cardServiceClient;

    private final ProcessingMapper processingMapper;

    @Override
    @Transactional
    public ProcessingDto issueCard(Long accountId) {
        val accountDto = accountServiceClient.getAccount(accountId).orElseThrow(() -> new IllegalArgumentException("Has no such account with id: " + accountId));
        ProcessingEntity processingEntity = new ProcessingEntity(accountId);
        String cardNumber = cardServiceClient.generateCardNumber();
        processingEntity.setCard(cardNumber);
        processingRepository.save(processingEntity);
        return processingMapper.toDto(processingEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public void spendMoney(String cardNumber, BigDecimal sum) {
        ProcessingEntity processingEntity = processingRepository.findByCard(cardNumber);
        accountServiceClient.withdrawAccount(processingEntity.getAccountId(), sum);
    }

    @Override
    @Transactional(readOnly = true)
    public ProcessingDto getProcessing(Long accountId) {
        ProcessingEntity processingEntity = processingRepository.findByAccountId(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Has no such processing with account id: " + accountId));
        return processingMapper.toDto(processingEntity);
    }
}
