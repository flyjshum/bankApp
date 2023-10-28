package com.example.bankapp.service;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.ClientEntity;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll();

    AccountDto getById(Long id);

    List<AccountDto> findByName(String name);

    AccountDto createAccount(AccountDto accountDto);

    AccountEntity updateAccount(Long id, AccountDto accountDto);

    void deleteAccount(Long id);
}
