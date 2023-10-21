package com.example.bankapp.controller;

import com.example.bankapp.dtos.AccountDto;

import com.example.bankapp.entities.AccountEntity;

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
    public List<AccountEntity> getAll() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName("Vklad");
        // accountEntity.setClient(12233);  не получилось, ошибка из-за типа
        return List.of(accountEntity);
    }


    @GetMapping("/search")
    public List<AccountEntity> getByName(@RequestParam String name) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName("Вклад 1");
        return List.of(accountEntity);
    }

    @PostMapping("/")
    public AccountDto add(@RequestBody AccountDto accountDto) {
        System.out.println(accountDto.getName());
        return null;
    }

    @PutMapping("/{client_id}")
    public AccountDto update(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        return null;
    }

    //наверное нельзя удалять счета.. статус будет меняться на неактивный
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }
}

