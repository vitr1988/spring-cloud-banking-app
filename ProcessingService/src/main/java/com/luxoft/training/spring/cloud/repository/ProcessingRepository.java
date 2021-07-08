package com.luxoft.training.spring.cloud.repository;

import com.luxoft.training.spring.cloud.domain.ProcessingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProcessingRepository extends JpaRepository<ProcessingEntity, Long> {
    ProcessingEntity findByCard(String card);

    List<ProcessingEntity> findByAccountIdIn(List<Long> accountIdList);

    Optional<ProcessingEntity> findByAccountId(Long accountId);
}
