package com.example.bankapp.controller;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.dtos.ManagerDto;
import com.example.bankapp.entities.ClientEntity;
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
    public List<ManagerDto> getAll() {
        List<ManagerDto> managers = managerService.getAll();
        return managers;
    }


    @GetMapping("/search")
    public List<ManagerDto> getByLastName(@RequestParam String lastName) {
        return managerService.findByLastName(lastName);
    }

    @GetMapping("/{id}")
    public ManagerDto getById(@PathVariable Long id) {
        return managerService.getById(id);
    }

    @PostMapping("/")
    public ManagerDto add(@RequestBody ManagerDto managerDto) {
        return managerService.createManager(managerDto);
    }

    @PutMapping("/{id}")
    public ManagerEntity update(@PathVariable Long id, @RequestBody ManagerDto managerDto) {
        return managerService.updateManager(id, managerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        managerService.deleteManager(id);
    }

}


