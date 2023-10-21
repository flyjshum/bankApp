package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.ProductDto;
import com.example.bankapp.mappers.ClientMapper;
import com.example.bankapp.mappers.ProductMapper;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.service.ClientService;
import com.example.bankapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAll() {

        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

}