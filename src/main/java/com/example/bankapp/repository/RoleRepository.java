package com.example.bankapp.repository;

import com.example.bankapp.entities.RoleEntity;
import com.example.bankapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String username);
    Optional<RoleEntity> findById(Long id);
}
