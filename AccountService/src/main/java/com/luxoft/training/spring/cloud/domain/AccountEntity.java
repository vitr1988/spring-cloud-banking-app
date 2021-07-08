package com.luxoft.training.spring.cloud.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class AccountEntity implements Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long clientId;
    private BigDecimal balance;

    public AccountEntity(Long clientId) {
        this.clientId = clientId;
        this.balance = BigDecimal.ZERO;
    }
}
