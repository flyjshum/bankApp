package com.example.bankapp.repository;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.AgreementEntity;
import com.example.bankapp.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgreementRepository extends JpaRepository<AgreementEntity, Long> {
    List<AgreementEntity> findByClientId(Long id);

    Optional<AgreementEntity> findById(Long id);


    List<AgreementEntity> findByAccountId(Long id);

    List<AgreementEntity> findByProductId(Long id);

    List<AgreementEntity> findByManagerId(Long id);
}

