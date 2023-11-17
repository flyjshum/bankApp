package com.example.bankapp.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@Schema(description = "ДТО Продукта")
public class ProductDto {
    @Schema(description = "Это id продукта", example = "2")
    private Long id;
    @Schema(description = "Это название продукта", example = "Пенсионный вклад")
    private String name;
    @Schema(description = "Статус продукта 1 - доступный к оформлению, 0 - в архиве", example = "1")
    private int status;
    @Schema(description = "Валюта продукта: RUB 1, EUR 10, USD 7", example = "1")
    private int currencyCode;
    @Schema(description = "Годовой размер процентной ставки по продукту", example = "6,5")
    private double interestRate;
    @Schema(description = "Минимальный размер депозита", example = "50000")
    private BigDecimal limitMin;
    @Schema(description = "Минимальный размер депозита", example = "500000000")
    private BigDecimal limitMax;
}

