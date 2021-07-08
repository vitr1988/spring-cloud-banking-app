package com.luxoft.training.spring.cloud.dto;

import lombok.Data;

@Data
public class ProcessingDto {
    private Long id;
    private String card;
    private Long accountId;
}
