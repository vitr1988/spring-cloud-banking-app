package com.luxoft.training.spring.cloud.domain;

import java.math.BigDecimal;

public interface Account {
    Long getId();
    Long getClientId();
    BigDecimal getBalance();
}
