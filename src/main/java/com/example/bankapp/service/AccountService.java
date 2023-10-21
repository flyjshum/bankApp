package com.example.bankapp.service;

import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.entities.AccountEntity;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll();
}
