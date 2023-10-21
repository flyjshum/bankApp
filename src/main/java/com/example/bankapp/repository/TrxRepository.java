package com.example.bankapp.repository;
import com.example.bankapp.entities.TrxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrxRepository extends JpaRepository<TrxEntity, Long> {
}
