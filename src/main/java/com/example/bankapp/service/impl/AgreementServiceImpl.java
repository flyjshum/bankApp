package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.AgreementDto;
import com.example.bankapp.entities.AgreementEntity;
import com.example.bankapp.mappers.AgreementMapper;
import com.example.bankapp.repository.AgreementRepository;
import com.example.bankapp.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    @Override
    public List<AgreementDto> getAll() {
        return null;
    }

    //   private final AgreementRepository agreementRepository;
 //   private final AgreementMapper agreementMapper;

  //  @Override
 //   public List<AgreementDto> getAll() {
  //      return agreementRepository.findAll().stream()
    //            .map(agreementMapper::toDto)
    //            .toList();
  //  }

}
