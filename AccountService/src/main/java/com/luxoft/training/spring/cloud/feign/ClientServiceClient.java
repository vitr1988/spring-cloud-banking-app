package com.luxoft.training.spring.cloud.feign;

import com.luxoft.training.spring.cloud.feign.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("ClientService")
public interface ClientServiceClient {

    @GetMapping("/get/{id}")
    Optional<ClientDto> findById(@PathVariable Long id);

}
