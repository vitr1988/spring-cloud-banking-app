package com.luxoft.training.spring.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("CardService")
public interface CardServiceClient {

    @GetMapping("/create")
    String generateCardNumber();
}
