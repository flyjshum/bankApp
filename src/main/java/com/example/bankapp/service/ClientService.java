package com.example.bankapp.service;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.ClientEntity;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();

    ClientDto getById(Long id);


}
