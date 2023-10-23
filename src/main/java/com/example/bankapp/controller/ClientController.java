package com.example.bankapp.controller;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/")
    public List<ClientDto> getAll() {
        List<ClientDto> clients = clientService.getAll();
        return clients;
    }

    @GetMapping("/search")
    public List<ClientEntity> getByFirstName(@RequestParam String firstName) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("123");
        return List.of(clientEntity);// return clientService.getAll();
    }
    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable Long id) {
        return clientService.getById(id);

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
