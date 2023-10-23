package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.mappers.ClientMapper;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
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
    public ClientDto getById(Long id) {
        Optional<ClientEntity> optClientEntity = clientRepository.findById(id);
        if (optClientEntity.isPresent()) {
            return clientMapper.toDto(optClientEntity.get());
        } else {
            log.info("Client id ={} is not found", id);
            throw new NotFoundException("Client not found");
        }
    }

}
