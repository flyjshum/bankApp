package com.example.bankapp.controller;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.ProductDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.ProductEntity;
import com.example.bankapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public List<ProductDto> getAll() {
        List<ProductDto> products = productService.getAll();
        return products;
    }
    @GetMapping("/search")
    public List<ProductDto> getByName(@RequestParam String name) {
        return productService.findByName(name);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping("/")
    public ProductDto add(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("/{id}")
    public ProductEntity update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


}
