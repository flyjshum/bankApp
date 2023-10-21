package com.example.bankapp.controller;

import com.example.bankapp.dtos.ManagerDto;
import com.example.bankapp.entities.ManagerEntity;
import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;


    @GetMapping("/")
    public List<ManagerEntity> getAll() {
        ManagerEntity managerEntity = new ManagerEntity();
        managerEntity.setFirstName("Николай");
        return List.of(managerEntity);// return clientService.getAll();
    }

    @GetMapping("/search")
    public List<ManagerEntity> getByFirstName(@RequestParam String firstName) {
        ManagerEntity managerEntity = new ManagerEntity();
        managerEntity.setFirstName("123");
        return List.of(managerEntity);// return clientService.getAll();
    }

    @PostMapping("/")
    public ManagerDto add(@RequestBody ManagerDto managerDto) {
        System.out.println(managerDto.getFirstName());
        return null;
    }

    @PutMapping("/{id}")
    public ManagerDto update(@PathVariable Long id, @RequestBody ManagerDto managerDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }
}


