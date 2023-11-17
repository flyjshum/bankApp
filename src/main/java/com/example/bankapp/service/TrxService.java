package com.example.bankapp.service;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.TrxDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.TrxEntity;
import java.util.List;

public interface TrxService {
    List<TrxDto> getAll();
    TrxDto getById(Long id);
    List<TrxDto> findByAccountId(Long id);
    List<TrxDto> findByStatus(int status);
    TrxDto createTrx(TrxDto trxDto);
    TrxEntity updateTrx(Long id, TrxDto trxDto);
    void deleteTrx(Long id);
}
