package com.example.bankapp.mappers;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.AgreementDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.AgreementEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
    // @Mapping(source = "firstName", target = "n")
    AgreementDto toDto(AgreementEntity entity);
    AgreementEntity toEntity(AgreementDto agreementDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    @Mapping(target ="client", ignore = true)
    @Mapping(target ="account", ignore = true)
    @Mapping(target ="product", ignore = true)
    @Mapping(target ="sum", ignore = true)
    void updateEntity(@MappingTarget AgreementEntity agreementEntity, AgreementDto agreementDto);
}
