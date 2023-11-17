package com.example.bankapp.controller;

import com.example.bankapp.dtos.*;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.ClientEntity;
import com.example.bankapp.entities.TrxEntity;
import com.example.bankapp.service.TrxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/trx")
@RequiredArgsConstructor
public class TrxController {

    private final TrxService trxService;

    @Operation(summary = "Получить список всех транзакций")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции найдены",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrxDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Транзакции не найдены",
                    content = @Content) })
    @GetMapping("/")
    public List<TrxDto> getAll() {
        List<TrxDto> trxs = trxService.getAll();
        return trxs;
    }

    @Operation(summary = "Получить все транзакции по id счета")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции найдены",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrxDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Транзакции не найдены",
                    content = @Content) })
    @GetMapping("/search")
    public List<TrxDto> getByAccountId(@Parameter(description = "id счета, по которому ведется поиск транзакций", example = "2") @RequestParam Long id) {
        return trxService.findByAccountId(id);
    }

    @Operation(summary = "Получить транзакцию по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакция найдена",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrxDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Транзакция не найдена",
                    content = @Content) })
    @GetMapping("/{id}")
    public TrxDto getById(@Parameter(description = "id транзакции, по которому ведется поиск", example = "2")  @PathVariable Long id) {
        return trxService.getById(id);
    }

    @Operation(summary = "Создать запись о новой транзакции")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакция создана",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrxDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Транзакция не создана",
                    content = @Content) })
    @PostMapping("/")
    public TrxDto add(@RequestBody TrxDto trxDto) {
        return trxService.createTrx(trxDto);
    }


    @Operation(summary = "Обновить данные транзакции")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные транзакции обновлены",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrxDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные транзацкии не обновлены",
                    content = @Content) })
    @PutMapping("/{id}")
    public TrxEntity update(@Parameter(description = "id транзакции, которую надо обновить", example = "2")
                                @PathVariable Long id, @RequestBody TrxDto trxDto) {
        return trxService.updateTrx(id, trxDto);
    }

    @Operation(summary = "Удалить данные о транзакции по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные об операции удалены",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrxDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные об операции не удалены",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "id транзакции, которую надо удалить", example = "2") @PathVariable Long id) {
        trxService.deleteTrx(id);
    }


}
