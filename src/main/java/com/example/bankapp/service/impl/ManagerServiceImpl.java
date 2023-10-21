package com.example.bankapp.service.impl;
import com.example.bankapp.dtos.ManagerDto;
import com.example.bankapp.entities.ManagerEntity;
import com.example.bankapp.mappers.ManagerMapper;
import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    @Override
    public List<ManagerDto> getAll() {

        return managerRepository.findAll().stream()
                .map(managerMapper::toDto)
                .toList();
    }

}

