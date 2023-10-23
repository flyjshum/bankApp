package com.example.bankapp.service;
import com.example.bankapp.dtos.ManagerDto;
import com.example.bankapp.entities.ManagerEntity;

import java.util.List;

public interface ManagerService {
    List<ManagerDto> getAll();
}
