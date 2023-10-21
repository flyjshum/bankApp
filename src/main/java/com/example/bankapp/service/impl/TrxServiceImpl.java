package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.TrxDto;
import com.example.bankapp.entities.TrxEntity;
import com.example.bankapp.mappers.TrxMapper;
import com.example.bankapp.repository.TrxRepository;
import com.example.bankapp.service.TrxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrxServiceImpl implements TrxService {
    private final TrxRepository trxRepository;
    private final TrxMapper trxMapper;

    @Override
    public List<TrxDto> getAll() {

        return trxRepository.findAll().stream()
                .map(trxMapper::toDto)
                .toList();
    }

}

