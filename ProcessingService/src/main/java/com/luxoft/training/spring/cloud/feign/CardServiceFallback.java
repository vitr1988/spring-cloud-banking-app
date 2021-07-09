package com.luxoft.training.spring.cloud.feign;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CardServiceFallback implements CardServiceClient {

    @Override
    public String generateCardNumber() {
        return StringUtils.EMPTY;
    }
}
