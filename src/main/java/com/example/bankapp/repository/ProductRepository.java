package com.example.bankapp.repository;


import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByName(String name);

    Optional<ProductEntity> findById(Long id);

    // @Query(value = "SELECT ce FROM ClientEntity ce where ce.email=:email")
    Optional<ProductEntity> getByStatus(int status);
}
