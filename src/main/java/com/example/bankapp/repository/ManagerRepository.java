package com.example.bankapp.repository;

import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {
    List<ManagerEntity> findByLastName(String lastName);

    Optional<ManagerEntity> findById(Long id);

    // @Query(value = "SELECT ce FROM ClientEntity ce where ce.email=:email")
    Optional<ManagerEntity> getByStatus(int status);
}
