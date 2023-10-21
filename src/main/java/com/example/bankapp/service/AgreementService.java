package com.example.bankapp.service;

import com.example.bankapp.dtos.AgreementDto;
import com.example.bankapp.entities.AgreementEntity;
import com.example.bankapp.entities.ClientEntity;

import java.util.List;

public interface AgreementService {
    List<AgreementDto> getAll();

}
