package com.example.bankapp.controller;
import com.example.bankapp.dtos.ProductDto;
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
    public List<ProductEntity> getByName(@RequestParam String name) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Vklad");
        return List.of(productEntity);
    }

    @PostMapping("/")
    public ProductDto add(@RequestBody ProductDto productDto) {
        System.out.println(productDto.getName());
        return null;
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }
}
