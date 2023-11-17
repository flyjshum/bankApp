package com.example.bankapp.controller;

import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @Operation(summary = "Получить список всех клиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиенты найдены",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Клиенты не найдены",
                    content = @Content) })
    @GetMapping("/")
    public List<ClientDto> getAll() {
        List<ClientDto> clients = clientService.getAll();
        return clients;
    }
    @Operation(summary = "Получить клиента по фамилии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент найден",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Клиент не найден",
                    content = @Content) })
    @GetMapping("/search")
    public List<ClientDto> getByLastName(@Parameter(description = "Фамилия клиента, по которому ведется поиск", example = "Иванов") @RequestParam String lastName) {
        return clientService.findByLastName(lastName);
    }
    @Operation(summary = "Получить клиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент найден",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Клиент не найден",
                    content = @Content) })
    @GetMapping("/{id}")
    public ClientDto getById(@Parameter(description = "id клиента, по которому ведется поиск", example = "2") @PathVariable Long id) {
        return clientService.getById(id);
    }
    @Operation(summary = "Создать запись о новом клиенте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент добавлен",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Клиент не добавлен",
                    content = @Content) })
    @PostMapping("/")
    public ClientDto add(@RequestBody ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @Operation(summary = "Обновить данные клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные клиента обновлены",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные клиента не обновлены",
                    content = @Content) })
    @PutMapping("/{id}")
    public ClientEntity update(@Parameter(description = "id клиента, которого необходимо обновить", example = "2") @PathVariable Long id, @RequestBody ClientDto clientDto) {
        return clientService.updateClient(id, clientDto);
    }
    @Operation(summary = "Удалить данные клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные клиента удалены",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные клиента не удалены",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "id клиента, которого надо удалить", example = "2") @PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
