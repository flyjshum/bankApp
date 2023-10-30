package com.example.bankapp.controller;

import com.example.bankapp.dtos.AgreementDto;
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
    public List<AgreementDto> getAll() {
        List<AgreementDto> agreements = agreementService.getAll();
        return agreements;
    }

    @GetMapping("/search")
    public List<AgreementDto> getByClientId(@RequestParam Long id) {
        return agreementService.findByClientId(id);
    }

    @PostMapping("/")
    public AgreementDto add(@RequestBody AgreementDto agreementDto) {
        return agreementService.createAgreement(agreementDto);
    }

    @PutMapping("/{id}")
    public AgreementEntity update(@PathVariable Long id, @RequestBody AgreementDto agreementDto) {
        return agreementService.updateAgreement(id, agreementDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        agreementService.deleteAgreement(id);
    }

}
