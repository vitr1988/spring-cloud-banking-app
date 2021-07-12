package com.luxoft.training.spring.cloud.service;

import com.luxoft.training.spring.cloud.domain.Account;
import com.luxoft.training.spring.cloud.domain.AccountEntity;
import com.luxoft.training.spring.cloud.feign.ClientServiceClient;
import com.luxoft.training.spring.cloud.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientServiceClient clientServiceClient;

    @Override
    @Transactional
    public Long createAccount(Long clientId) {
        val clientDto = clientServiceClient.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Has no client with id: " + clientId));
        AccountEntity accountEntity = new AccountEntity(clientDto.getId());
        accountRepository.save(accountEntity);
        return accountEntity.getId();
    }

    @Override
    @Transactional
    public void depositAccount(Long accountId, BigDecimal money) {
        accountRepository.addBalance(accountId, money);
    }

    @Override
    @Transactional
    public void withdrawAccount(Long accountId, BigDecimal money) {
        accountRepository.addBalance(accountId, money.multiply(BigDecimal.valueOf(-1)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id).map(Account.class::cast);
    }
}
