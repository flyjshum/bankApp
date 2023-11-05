package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.AccountAgreementDto;
import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.TrxDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.AgreementEntity;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.TrxEntity;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.exception.ValidationException;
import com.example.bankapp.mappers.AccountMapper;
import com.example.bankapp.mappers.TrxMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TrxRepository;
import com.example.bankapp.service.TrxService;
import jakarta.transaction.Transactional;
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
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

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
            throw new NotFoundException("Trx " + id + "is not found");
        }
    }

    @Override
    public List<TrxDto> findByAccountId(Long accountId) {
        List<TrxEntity> trxEntities = trxRepository.findByAccountId(accountId);
        if (trxEntities.isEmpty()) {
            throw new NotFoundException("There is no trx for account" + accountId);
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
            throw new NotFoundException("There is no trx with status" + status);
        } else {
            return trxEntities.stream()
                    .map(trxMapper::toDto)
                    .toList();
        }
    }

    @Transactional
    @Override
    public TrxDto createTrx(TrxDto trxDto) {
        // Optional<TrxEntity> optTrxEntity = trxRepository.getByEmail(trxDto.getEmail());
        //  if (optTrxEntity.isEmpty()) {
        TrxEntity trxEntity = trxMapper.toEntity(trxDto);
        Optional<AccountEntity> optAccountEntity = accountRepository.findById(trxDto.getAccountId());
        AccountEntity accountEntity = optAccountEntity.get();
        double balanceBeforeTrx = accountEntity.getBalance();

        //если операция дебитовая  тип 1
        if (trxEntity.getType() == 1) {
            accountEntity.setBalance(balanceBeforeTrx + trxDto.getAmount());
        }  else  {
        //если операция кредитовая  тип 2
            accountEntity.setBalance(balanceBeforeTrx - trxDto.getAmount());
        }
        trxEntity.setAccount(accountEntity);

        AccountEntity savedAccountEntity = accountRepository.saveAndFlush(accountEntity);
        TrxEntity savedTrx = trxRepository.save(trxMapper.toEntity(trxDto));
        log.info("Created and saved Trx with ID= {}", savedTrx.getId());
        return trxMapper.toDto(savedTrx);
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
        throw new NotFoundException("Trx cannot be updated," + id + "is not found");

    }

    @Override
    public void deleteTrx(Long id) {
        Optional<TrxEntity> optTrxEntity = trxRepository.findById(id);
        if (optTrxEntity.isPresent()) {
            trxRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Trx" + id + "is " +
                "not found");
    }

}

