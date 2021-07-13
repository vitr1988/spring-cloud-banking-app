package com.luxoft.training.spring.cloud;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class WithdrawEvent extends AbstractFinancialEvent {

    public WithdrawEvent(String originService, String destinationService, BigDecimal sum) {
        super(originService, destinationService, sum);
    }
}
