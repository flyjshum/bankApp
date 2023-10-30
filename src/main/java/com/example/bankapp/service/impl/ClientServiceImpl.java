package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.exception.ValidationException;
import com.example.bankapp.mappers.ClientMapper;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.service.ClientService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public List<ClientDto> findByLastName(String lastName) {
        List<ClientEntity> clientEntities = clientRepository.findByLastName(lastName);
        if (clientEntities.isEmpty()) {
            log.info("Client with lastName ={} is not found", lastName);
            throw new NotFoundException("Client with this with lastName not found");
        } else {
            return clientEntities.stream()
                    .map(clientMapper::toDto)
                    .toList();
        }
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Optional<ClientEntity> optClientEntity = clientRepository.getByEmail(clientDto.getEmail());
        if (optClientEntity.isEmpty()) {
            ClientEntity savedClient = clientRepository.save(clientMapper.toEntity(clientDto));
            log.info("Created and saved client with ID= {}", savedClient.getId());
            return clientMapper.toDto(savedClient);
        } else {
            log.info("This email = {} is already occupied", clientDto.getEmail());
            throw new ValidationException("Client cannot be created, email is occupied");
        }
    }

    @Override
    public ClientEntity updateClient(Long id, ClientDto clientDto) {
        Optional<ClientEntity> optClientEntity = clientRepository.findById(id);
        if (optClientEntity.isPresent()) {
            ClientEntity clientEntity = optClientEntity.get();
            clientMapper.updateEntity(clientEntity, clientDto);
            clientRepository.save(clientEntity);
            log.info("Client with ID {} is updated", id);
            return clientEntity;
        }

        log.info("This id = {} is not found", id);
        throw new NotFoundException("Client cannot be updated, id is not found");

    }

    @Override
    public void deleteClient(Long id) {
        Optional<ClientEntity> optClientEntity = clientRepository.findById(id);
        if (optClientEntity.isPresent()) {
            clientRepository.deleteById(id);
            return;
        }
        log.info("Client id ={} is not found", id);
        throw new NotFoundException("Client not found");
    }

}





