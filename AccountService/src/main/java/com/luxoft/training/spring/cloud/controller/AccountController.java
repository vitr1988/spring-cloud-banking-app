package com.luxoft.training.spring.cloud.controller;

import com.luxoft.training.spring.cloud.FundEvent;
import com.luxoft.training.spring.cloud.domain.Account;
import com.luxoft.training.spring.cloud.service.AccountService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.bus.BusBridge;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RefreshScope
public class AccountController {

    private final AccountService accountService;
    private final BusBridge busClient;

    @PreAuthorize("hasAuthority('ACCOUNT_WRITE')")
    @GetMapping("/create")
    public Long createAccount(@RequestParam("client_id") Long clientId) {
        return accountService.createAccount(clientId);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_PROCESS')")
    @GetMapping("/fund/{id}")
    public void depositAccount(@PathVariable Long id, @RequestParam BigDecimal sum) {
        try {
            accountService.depositAccount(id, sum);
        } finally {
            busClient.send(new FundEvent("AccountService", "HistoryService", sum));
        }
    }

    @PreAuthorize("hasAuthority('ACCOUNT_PROCESS')")
    @GetMapping("/checkout/{id}")
    public void withdrawAccount(@PathVariable Long id, @RequestParam BigDecimal sum) {
        accountService.withdrawAccount(id, sum);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_READ')")
    @GetMapping("/get/{id}")
    public Optional<Account> getAccount(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @Async
    @Bulkhead(name = "account", type = Bulkhead.Type.THREADPOOL)
    @TimeLimiter(name = "account")
    @CircuitBreaker(name = "account", fallbackMethod = "fallbackMethod")
    @GetMapping("/fallback/{id}")
    public CompletableFuture<Long> fallback(@PathVariable Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Has no accounts with negative ids");
        }
        return CompletableFuture.completedFuture(id);
    }

    private CompletableFuture<Long> fallbackMethod(Long id, IllegalArgumentException e) {
        return CompletableFuture.completedFuture(-1L);
    }
}
