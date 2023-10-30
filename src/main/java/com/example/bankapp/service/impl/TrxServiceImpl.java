package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.TrxDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.TrxEntity;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.exception.ValidationException;
import com.example.bankapp.mappers.TrxMapper;
import com.example.bankapp.repository.TrxRepository;
import com.example.bankapp.service.TrxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrxServiceImpl implements TrxService {
    private final TrxRepository trxRepository;
    private final TrxMapper trxMapper;

    @Override
    public List<TrxDto> getAll() {

        return trxRepository.findAll().stream()
                .map(trxMapper::toDto)
                .toList();
    }

    @Override
    public TrxDto getById(Long id) {
        Optional<TrxEntity> optTrxEntity = trxRepository.findById(id);
        if (optTrxEntity.isPresent()) {
            return trxMapper.toDto(optTrxEntity.get());
        } else {
            log.info("Trx id ={} is not found", id);
            throw new NotFoundException("Trx not found");
        }
    }

    @Override
    public List<TrxDto> findByAccountId(Long accountId) {
        List<TrxEntity> trxEntities = trxRepository.findByAccountId(accountId);
        if (trxEntities.isEmpty()) {
            log.info("Trx of account id ={} is not found", accountId);
            throw new NotFoundException("There is no trx for this account id");
        } else {
            return trxEntities.stream()
                    .map(trxMapper::toDto)
                    .toList();
        }
    }

    @Override
    public List<TrxDto> findByStatus(int status) {
        List<TrxEntity> trxEntities = trxRepository.findByStatus(status);
        if (trxEntities.isEmpty()) {
            log.info("Trx with status id ={} not found", status);
            throw new NotFoundException("There is no trx with such status");
        } else {
            return trxEntities.stream()
                    .map(trxMapper::toDto)
                    .toList();
        }
    }

    @Override
    // когда создаются трх?
    public TrxDto createTrx(TrxDto trxDto) {
        // Optional<TrxEntity> optTrxEntity = trxRepository.getByEmail(trxDto.getEmail());
        //  if (optTrxEntity.isEmpty()) {
        TrxEntity savedTrx = trxRepository.save(trxMapper.toEntity(trxDto));
        log.info("Created and saved Trx with ID= {}", savedTrx.getId());
        return trxMapper.toDto(savedTrx);
        //   } else {
        //       log.info("");
        //       throw new ValidationException("Trx cannot be created");
        //   }
    }

    @Override
    public TrxEntity updateTrx(Long id, TrxDto clientDto) {
        Optional<TrxEntity> optTrxEntity = trxRepository.findById(id);
        if (optTrxEntity.isPresent()) {
            TrxEntity trxEntity = optTrxEntity.get();
            trxMapper.updateEntity(trxEntity, clientDto);
            trxRepository.save(trxEntity);
            log.info("Trx with ID {} is updated", id);
            return trxEntity;
        }

        log.info("This id = {} is not found", id);
        throw new NotFoundException("Client cannot be updated, id is not found");

    }

    @Override
    public void deleteTrx(Long id) {
        Optional<TrxEntity> optTrxEntity = trxRepository.findById(id);
        if (optTrxEntity.isPresent()) {
            trxRepository.deleteById(id);
            return;
        }
        log.info("Trx id ={} is not found", id);
        throw new NotFoundException("Trx not found");
    }

}

