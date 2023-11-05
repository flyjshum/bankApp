package com.example.bankapp.mappers;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.ProductDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.ProductEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // @Mapping(source = "firstName", target = "n")
    ProductDto toDto(ProductEntity entity);
    ProductEntity toEntity(ProductDto productDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    @Mapping(target ="name", ignore = true)
    @Mapping(target ="currencyCode", ignore = true)
    @Mapping(target ="interestRate", ignore = true)
    @Mapping(target ="limit", ignore = true)
    void updateEntity(@MappingTarget ProductEntity productEntity, ProductDto productDto);
}
