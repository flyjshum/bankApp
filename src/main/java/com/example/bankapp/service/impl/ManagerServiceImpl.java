package com.example.bankapp.service.impl;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.ManagerDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.ManagerEntity;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.exception.ValidationException;
import com.example.bankapp.mappers.ManagerMapper;
import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    @Override
    public List<ManagerDto> getAll() {

        return managerRepository.findAll().stream()
                .map(managerMapper::toDto)
                .toList();
    }

    @Override
    public ManagerDto getById(Long id) {
        Optional<ManagerEntity> optManagerEntity = managerRepository.findById(id);
        if (optManagerEntity.isPresent()) {
            return managerMapper.toDto(optManagerEntity.get());
        } else {
            log.info("Manager id ={} is not found", id);
            throw new NotFoundException("Manager not found");
        }
    }

    @Override
    public List<ManagerDto> findByLastName(String lastName) {
        List<ManagerEntity> managerEntities = managerRepository.findByLastName(lastName);
        if (managerEntities.isEmpty()) {
            log.info("Manager with lastName ={} is not found", lastName);
            throw new NotFoundException("Manager with this lastName not found");
        } else {
            return managerEntities.stream()
                    .map(managerMapper::toDto)
                    .toList();
        }
    }

    @Override
    public ManagerDto createManager(ManagerDto managerDto) {
      //  Optional<ManagerEntity> optManagerEntity = managerRepository.getByEmail(managerDto.getEmail());
     //   if (optManagerEntity.isEmpty()) {
            ManagerEntity savedManager = managerRepository.save(managerMapper.toEntity(managerDto));
            log.info("Created and saved client with ID= {}", savedManager.getId());
            return managerMapper.toDto(savedManager);
     //   } else {
     //       log.info("This email = {} is already occupied", managerDto.getEmail());
     //       throw new ValidationException("Manager cannot be created, email is occupied");
     //   }
    }

    @Override
    public ManagerEntity updateManager(Long id, ManagerDto managerDto) {
        Optional<ManagerEntity> optManagerEntity = managerRepository.findById(id);
        if (optManagerEntity.isPresent()) {
            ManagerEntity managerEntity = optManagerEntity.get();
            managerMapper.updateEntity(managerEntity, managerDto);
            managerRepository.save(managerEntity);
            log.info("Manager with ID {} is updated", id);
            return managerEntity;
        }

        log.info("This id = {} is not found", id);
        throw new NotFoundException("Manager cannot be updated, id is not found");

    }

    @Override
    public void deleteManager(Long id) {
        Optional<ManagerEntity> optManagerEntity = managerRepository.findById(id);
        if (optManagerEntity.isPresent()) {
            managerRepository.deleteById(id);
            return;
        }
        log.info("Manager id ={} is not found", id);
        throw new NotFoundException("Manager not found");
    }



}

