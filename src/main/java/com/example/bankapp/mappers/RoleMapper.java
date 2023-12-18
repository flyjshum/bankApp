package com.example.bankapp.mappers;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.RoleDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.RoleEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    // @Mapping(source = "firstName", target = "n")
    RoleDto toDto(RoleEntity entity);
    RoleEntity toEntity(RoleDto roleDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    @Mapping(target ="name", ignore = true)
    void updateEntity(@MappingTarget RoleEntity roleEntity, RoleDto roleDto);
}
