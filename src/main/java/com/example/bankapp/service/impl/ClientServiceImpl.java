package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.enums.Status;
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
            throw new NotFoundException("Client " + id + " is not found");
        }
    }

    @Override
    public List<ClientDto> findByLastName(String lastName) {
        List<ClientEntity> clientEntities = clientRepository.findByLastName(lastName);
        if (clientEntities.isEmpty()) {
            throw new NotFoundException("Client with with lastName" + lastName + " is not found");
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
            throw new ValidationException("Client cannot be created, email " + clientDto.getEmail() + " is occupied");
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
        throw new NotFoundException("Client " + id + " cannot be updated, id is not found");

    }

    @Override
    public void deleteClient(Long id) {
        Optional<ClientEntity> optClientEntity = clientRepository.findById(id);
        if (optClientEntity.isPresent()) {
            ClientEntity clientEntity = optClientEntity.get();
            clientEntity.setStatus(Status.INACTIVE);
            clientRepository.save(clientEntity);

            //   clientRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Client " + id + " is not found");
    }

}




