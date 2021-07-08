package com.luxoft.training.spring.cloud.mapper;

import com.luxoft.training.spring.cloud.domain.ClientEntity;
import com.luxoft.training.spring.cloud.dto.ClientDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ClientMapper {

    ClientDto toDto(ClientEntity clientEntity);

    List<ClientDto> toDtos(List<ClientEntity> clientEntityList);

    default Optional<ClientDto> toOptional(Optional<ClientEntity> clientEntity) {
        return clientEntity.map(this::toDto);
    }
}
