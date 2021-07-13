package com.luxoft.training.spring.cloud;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class FundEvent extends AbstractFinancialEvent {

    public FundEvent(String originService, String destinationService, BigDecimal sum) {
        super(originService, destinationService, sum);
    }
}
