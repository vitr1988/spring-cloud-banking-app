package com.luxoft.training.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AccountServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
