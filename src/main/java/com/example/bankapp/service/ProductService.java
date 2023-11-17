package com.example.bankapp.service;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.ProductDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    List<ProductDto> findByName(String name);
    ProductDto createProduct(ProductDto productDto);
    ProductEntity updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    boolean validateOptProduct(Optional<ProductEntity> optProductEntity);

}
