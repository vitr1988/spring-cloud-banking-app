package com.luxoft.training.spring.cloud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

import java.math.BigDecimal;

@NoArgsConstructor
public abstract class AbstractFinancialEvent extends RemoteApplicationEvent {

    private static Object source = new Object();

    @Getter
    private BigDecimal sum;

    public AbstractFinancialEvent(String originService, String destinationService, BigDecimal sum) {
        super(source, originService, destinationService);
        this.sum = sum;
    }
}
