package com.example.bankapp.repository;
import com.example.bankapp.entities.TrxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrxRepository extends JpaRepository<TrxEntity, Long> {
    List<TrxEntity> findByStatus(int status);

    Optional<TrxEntity> findById(Long id);

    // @Query(value = "SELECT ce FROM ClientEntity ce where ce.email=:email")
    List<TrxEntity> findByAccountId(Long id);
}
