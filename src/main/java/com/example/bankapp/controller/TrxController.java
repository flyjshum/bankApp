package com.example.bankapp.controller;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.TrxDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.TrxEntity;
import com.example.bankapp.service.TrxService;
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
    public List<TrxDto> getAll() {
        List<TrxDto> trxs = trxService.getAll();
        return trxs;
    }
    @GetMapping("/search")
    public List<TrxDto> getByAccountId(@RequestParam Long id) {
        return trxService.findByAccountId(id);
    }

    @GetMapping("/{id}")
    public TrxDto getById(@PathVariable Long id) {
        return trxService.getById(id);
    }

    @PostMapping("/")
    public TrxDto add(@RequestBody TrxDto trxDto) {
        return trxService.createTrx(trxDto);
    }

    @PutMapping("/{id}")
    public TrxEntity update(@PathVariable Long id, @RequestBody TrxDto trxDto) {
        return trxService.updateTrx(id, trxDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trxService.deleteTrx(id);
    }


}
