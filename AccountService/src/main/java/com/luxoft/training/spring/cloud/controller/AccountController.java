package com.luxoft.training.spring.cloud.controller;

import com.luxoft.training.spring.cloud.domain.Account;
import com.luxoft.training.spring.cloud.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/create")
    public Long createAccount(@RequestParam("client_id") Long clientId) {
        return accountService.createAccount(clientId);
    }

    @GetMapping("/fund/{id}")
    public void depositAccount(@PathVariable Long id, @RequestParam BigDecimal sum) {
        accountService.depositAccount(id, sum);
    }

    @GetMapping("/checkout/{id}")
    public void withdrawAccount(@PathVariable Long id, @RequestParam BigDecimal sum) {
        accountService.withdrawAccount(id, sum);
    }

    @GetMapping("/get/{id}")
    public Optional<Account> getAccount(@PathVariable Long id) {
        return accountService.findById(id);
    }
}
