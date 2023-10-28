package com.example.bankapp.mappers;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.ManagerDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.ManagerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
   // @Mapping(source = "firstName", target = "n")
    ManagerDto toDto(ManagerEntity entity);
    ManagerEntity toEntity(ManagerDto managerDto);
    void updateEntity(@MappingTarget ManagerEntity managerEntity, ManagerDto managerDto);
}
