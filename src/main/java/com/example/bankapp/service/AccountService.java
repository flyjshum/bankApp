package com.example.bankapp.service;

import com.example.bankapp.dtos.CreateAgreementRequest;
import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.entities.AccountEntity;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll();
    AccountDto getById(Long id);
    List<AccountDto> findByName(String name);
    AccountEntity createAccount();
    AccountEntity updateAccount(Long id, AccountDto accountDto);
    void deleteAccount(Long id);
}
