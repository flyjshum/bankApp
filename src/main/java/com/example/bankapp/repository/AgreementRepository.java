package com.example.bankapp.repository;

import com.example.bankapp.entities.AgreementEntity;
import com.example.bankapp.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepository extends JpaRepository<AgreementEntity, Long> {

}

