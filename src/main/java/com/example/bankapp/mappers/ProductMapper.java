package com.example.bankapp.mappers;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.ProductDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // @Mapping(source = "firstName", target = "n")
    ProductDto toDto(ProductEntity entity);
    ProductEntity toEntity(ProductDto productDto);
    void updateEntity(@MappingTarget ProductEntity productEntity, ProductDto productDto);
}
