package com.example.bankapp.repository;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

   // static List<ClientDto> findAllById(Long id) {
    //    return null;
   // }
}