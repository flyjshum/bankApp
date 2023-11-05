package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.ProductDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.ProductEntity;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.exception.ValidationException;
import com.example.bankapp.mappers.ClientMapper;
import com.example.bankapp.mappers.ProductMapper;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.service.ClientService;
import com.example.bankapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAll() {

        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto getById(Long id) {
        Optional<ProductEntity> optProductEntity = productRepository.findById(id);
        if (optProductEntity.isPresent()) {
            return productMapper.toDto(optProductEntity.get());
        } else {
            throw new NotFoundException("Product " + id + " is not found");
        }
    }

    @Override
    public List<ProductDto> findByName(String name) {
        List<ProductEntity> productEntities = productRepository.findByName(name);
        if (productEntities.isEmpty()) {
            throw new NotFoundException("Product with name" + name + " is not found");
        } else {
            return productEntities.stream()
                    .map(productMapper::toDto)
                    .toList();
        }
    }

    @Override
    public ProductDto createProduct(ProductDto clientDto) {
        ProductEntity savedClient = productRepository.save(productMapper.toEntity(clientDto));
        log.info("Created and saved client with ID= {}", savedClient.getId());
        return productMapper.toDto(savedClient);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductDto productDto) {
        Optional<ProductEntity> optProductEntity = productRepository.findById(id);
        if (optProductEntity.isPresent()) {
            ProductEntity productEntity = optProductEntity.get();
            productMapper.updateEntity(productEntity, productDto);
            productRepository.save(productEntity);
            log.info("Product with ID {} is updated", id);
            return productEntity;
        }
        throw new NotFoundException("Product cannot be updated, " + id + " is not found");

    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductEntity> optProductEntity = productRepository.findById(id);
        if (optProductEntity.isPresent()) {
            productRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Product " + id + " is not found");
    }
}