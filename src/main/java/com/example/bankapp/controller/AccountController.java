package com.example.bankapp.controller;

import com.example.bankapp.dtos.AccountAggrementDto;
import com.example.bankapp.dtos.AccountDto;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.AccountEntity;

import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.service.AccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/")
    public List<AccountDto> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/search")
    public List<AccountDto> getByName(@RequestParam String name) {
        return accountService.findByName(name);
    }

    @GetMapping("/{id}")
    public AccountDto getById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @PostMapping("/")
    public AccountDto add(@RequestBody AccountAggrementDto accountAggrementDto) {
        return accountService.createAccount(accountAggrementDto);
    }

    @PutMapping("/{client_id}")
    public AccountEntity update(@PathVariable Long client_id, @RequestBody AccountDto accountDto) {

        return accountService.updateAccount(client_id, accountDto);
    }

    //наверное нельзя удалять счета.. статус будет меняться на неактивный

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }


}

