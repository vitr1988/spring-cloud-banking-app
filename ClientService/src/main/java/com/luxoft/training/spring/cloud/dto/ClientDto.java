package com.luxoft.training.spring.cloud.dto;

import com.luxoft.training.spring.cloud.domain.Client;
import lombok.Data;

@Data
public class ClientDto implements Client {

    private Long id;
    private String name;
}
