package com.example.bankapp.mappers;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.ManagerDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.ManagerEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    // @Mapping(source = "firstName", target = "n")
    AccountDto toDto(AccountEntity entity);
    AccountEntity toEntity(AccountDto accountDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    @Mapping(target ="balance", ignore = true)
    void updateEntity(@MappingTarget AccountEntity accountEntity, AccountDto accountDto);
}
