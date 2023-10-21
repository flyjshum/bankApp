package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.mappers.ClientMapper;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getAll() {

        //List<ClientDto> clientDtos = new ArrayList<>();
        // for (int i = 0; i < clientEntities.size(); i++) {
        // ClientDto clientDto = clientMapper.toDto(clientEntities.get(i));
        //  clientDtos.add(clientDto);
        // }
        //   for (ClientEntity entity : clientEntities) {
        //      clientDtos.add(clientMapper.toDto(entity));
        //  }

        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    public List<ClientDto> getById() {
        return clientRepository.findById(500_858_858L).stream()
                .map(clientMapper::toDto)
                .toList();
    }

}
