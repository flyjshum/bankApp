package com.example.bankapp.service;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
}
