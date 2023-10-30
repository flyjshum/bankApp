package com.example.bankapp.mappers;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.TrxDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.TrxEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TrxMapper {
    TrxDto toDto(TrxEntity entity);
    TrxEntity toEntity(TrxDto trxDto);
    void updateEntity(@MappingTarget TrxEntity trxEntity, TrxDto trxDto);
}
