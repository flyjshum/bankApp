package com.example.bankapp.mappers;

import com.example.bankapp.dtos.ManagerDto;
import com.example.bankapp.entities.ManagerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
   // @Mapping(source = "firstName", target = "n")
    ManagerDto toDto(ManagerEntity entity);

    ManagerEntity toEntity(ManagerDto managerDto);
}
