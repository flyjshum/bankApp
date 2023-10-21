package com.example.bankapp.controller;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.AgreementEntity;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.service.AgreementService;
import com.example.bankapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agreement")
@RequiredArgsConstructor
public class AgreementController {
    private final AgreementService agreementService;

    @GetMapping("/")
    public List<AgreementEntity> getAll() {
        AgreementEntity agreementEntity = new AgreementEntity();
        agreementEntity.setInterestRate(88);
        return List.of(agreementEntity);// return clientService.getAll();
    }

    @GetMapping("/search")
    public List<AgreementEntity> getByClientId(@RequestParam Integer clientId) {
        // а если мы хотим искать по фамилии клиента, как это сделать?
        AgreementEntity agreementEntity = new AgreementEntity();
        agreementEntity.setStatus(1);
        return List.of(agreementEntity);// return clientService.getAll();
    }

    @PostMapping("/")
    public ClientDto add(@RequestBody ClientDto clientDto) {
        System.out.println(clientDto.getFirstName());
        return null;
    }

    @PutMapping("/{id}")
    public ClientDto update(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }
}
