package com.example.bankapp.service;
import com.example.bankapp.dtos.TrxDto;
import com.example.bankapp.entities.TrxEntity;
import java.util.List;

public interface TrxService {
    List<TrxDto> getAll();

}
