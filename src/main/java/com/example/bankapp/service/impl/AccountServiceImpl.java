package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.AccountAgreementDto;
import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.AgreementEntity;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.exception.ValidationException;
import com.example.bankapp.mappers.AccountMapper;
import com.example.bankapp.mappers.AgreementMapper;
import com.example.bankapp.mappers.ClientMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.AgreementRepository;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @Override
    public AccountDto getById(Long id) {
        Optional<AccountEntity> optAccountEntity = accountRepository.findById(id);
        if (optAccountEntity.isPresent()) {
            return accountMapper.toDto(optAccountEntity.get());
        } else {
            throw new NotFoundException("Account id = " + id + " is not found");
        }
    }

    @Override
    public List<AccountDto> findByName(String name) {
        List<AccountEntity> accountEntities = accountRepository.findByName(name);
        if (accountEntities.isEmpty()) {
            throw new NotFoundException("Account with name = " + name + " is not found");
        } else {
            return accountEntities.stream()
                    .map(accountMapper::toDto)
                    .toList();
        }
    }

    // @Override   // для одного клиента можно создать несколько аккаунтов,
    // не понятно какие if else создать
    //может по договору? если есть договор то автоматом должен создаваться счет

    // public AccountDto createAccount(AccountDto accountDto) {
//        Optional<AccountEntity> optAccountEntity = accountRepository.getByClientId(accountDto.getClientId());
//        if (optAccountEntity.isEmpty()) {
//            AccountEntity savedAccount = accountRepository.save(accountMapper.toEntity(accountDto));
//            log.info("Created and saved account with ID= {}", savedAccount.getId());
//            return accountMapper.toDto(savedAccount);
//        } else {
//            log.info("");
//            throw new ValidationException("");
//        }
//    }
    @Transactional
    public AccountDto createAccount(AccountAgreementDto accountAgreementDto) {

        AccountEntity accountEntity = accountMapper.toEntity(accountAgreementDto.getAccount());
        Optional<ClientEntity> optClientEntity = clientRepository.findById(accountAgreementDto.getAccount().getClientId());
        accountEntity.setClient(optClientEntity.get());
        AccountEntity savedAccountEntity = accountRepository.saveAndFlush(accountEntity);
        AgreementEntity agreementEntity = agreementMapper.toEntity(accountAgreementDto.getAgreement());
        agreementEntity.setAccount(savedAccountEntity);
        agreementRepository.saveAndFlush(agreementEntity);
        return accountMapper.toDto(accountEntity);
    }

    @Override
    public AccountEntity updateAccount(Long id, AccountDto accountDto) {
        Optional<AccountEntity> optAccountEntity = accountRepository.findById(id);
        if (optAccountEntity.isPresent()) {
            AccountEntity accountEntity = optAccountEntity.get();
            accountMapper.updateEntity(accountEntity, accountDto);
            accountRepository.save(accountEntity);
            log.info("Account with ID {} is updated", id);
            return accountEntity;
        }
        throw new NotFoundException("Account id = " + id + " ,cannot be updated, id is not found");

    }

    @Override
    public void deleteAccount(Long id) {
        Optional<AccountEntity> optAccountEntity = accountRepository.findById(id);
        if (optAccountEntity.isPresent()) {
            accountRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Account id = " + id + " is not found");

    }

}
