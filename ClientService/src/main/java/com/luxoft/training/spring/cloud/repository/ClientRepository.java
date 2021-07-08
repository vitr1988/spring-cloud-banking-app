package com.luxoft.training.spring.cloud.repository;

import com.luxoft.training.spring.cloud.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
