package com.example.bankapp.mappers;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.AgreementDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.AgreementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
    // @Mapping(source = "firstName", target = "n")
    AgreementDto toDto(AgreementEntity entity);

    AgreementEntity toEntity(AgreementDto agreementDto);
}
