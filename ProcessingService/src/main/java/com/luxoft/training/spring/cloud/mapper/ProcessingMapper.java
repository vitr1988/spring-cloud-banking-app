package com.luxoft.training.spring.cloud.mapper;

import com.luxoft.training.spring.cloud.domain.ProcessingEntity;
import com.luxoft.training.spring.cloud.dto.ProcessingDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProcessingMapper {

    ProcessingDto toDto(ProcessingEntity entity);
}
