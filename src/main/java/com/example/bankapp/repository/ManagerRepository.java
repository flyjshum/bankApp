package com.example.bankapp.repository;

import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {
}
