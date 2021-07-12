package com.luxoft.training.spring.cloud.controller;

import com.luxoft.training.spring.cloud.dto.ProcessingDto;
import com.luxoft.training.spring.cloud.service.ProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class ProcessingController {

    private final ProcessingService processingService;

    @PreAuthorize("hasAuthority('ACCOUNT_PROCESS')")
    @GetMapping("/issue/{accountId}")
    public ProcessingDto issueCard(@PathVariable Long accountId) {
        return processingService.issueCard(accountId);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_PROCESS')")
    @GetMapping("/checkout/{cardNumber}")
    public void spendMoney(@PathVariable String cardNumber, @RequestParam BigDecimal sum) {
        processingService.spendMoney(cardNumber, sum);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_PROCESS')")
    @GetMapping("/get")
    public ProcessingDto getProcessing(@RequestParam("account_id") Long accountId) {
        return processingService.getProcessing(accountId);
    }
}
