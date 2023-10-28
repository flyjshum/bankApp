package com.example.bankapp.mappers;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    // @Mapping(source = "firstName", target = "n")
    ClientDto toDto(ClientEntity entity);

    ClientEntity toEntity(ClientDto clientDto);

    void updateEntity(@MappingTarget ClientEntity clientEntity, ClientDto clientDto);
}
