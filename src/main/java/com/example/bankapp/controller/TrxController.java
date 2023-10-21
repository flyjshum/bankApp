package com.example.bankapp.controller;

import com.example.bankapp.dtos.TrxDto;
import com.example.bankapp.entities.TrxEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/trx")
@RequiredArgsConstructor
public class TrxController {

    private final TrxService trxService;

    @GetMapping("/")
    public List<TrxEntity> getAll() {
        TrxEntity trxEntity = new TrxEntity();
        trxEntity.setAmount(123.99);
        return List.of(trxEntity);// return clientService.getAll();
    }

    @GetMapping("/search")
    public List<TrxEntity> getByDate(@RequestParam Date created_at) {
        TrxEntity trxEntity = new TrxEntity();
        trxEntity.setDescription(122);
        return List.of(trxEntity);// return clientService.getAll();
    }

    @PostMapping("/")
    public TrxDto add(@RequestBody TrxDto trxDto) {
        System.out.println(trxDto.getDescription());
        return null;
    }

    @PutMapping("/{id}")
    public TrxDto update(@PathVariable Long id, @RequestBody TrxDto trxDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }
}
