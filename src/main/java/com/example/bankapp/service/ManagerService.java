package com.example.bankapp.service;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.ManagerDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.ManagerEntity;

import java.util.List;

public interface ManagerService {
    List<ManagerDto> getAll();
    ManagerDto getById(Long id);

    List<ManagerDto> findByLastName(String lastName);

    ManagerDto createManager(ManagerDto managerDto);

    ManagerEntity updateManager(Long id, ManagerDto managerDto);

    void deleteManager(Long id);

}
