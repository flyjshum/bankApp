package com.example.bankapp.service;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.ClientEntity;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();

    ClientDto getById(Long id);

    // ClientDto getByEmail(String email);

    List<ClientDto> findByLastName(String lastName);

    ClientDto createClient(ClientDto clientDto);

    ClientEntity updateClient(Long id, ClientDto clientDto);

    void deleteClient(Long id);


}
