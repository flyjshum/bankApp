package com.example.bankapp.mappers;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.UserDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // @Mapping(source = "firstName", target = "n")
    UserDto toDto(UserEntity entity);
    UserEntity toEntity(UserDto userDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    @Mapping(target ="username", ignore = true)
    void updateEntity(@MappingTarget UserEntity userEntity, UserDto userDto);
}
