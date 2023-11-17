package com.example.bankapp.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Schema(description = "ДТО Менеджера")
public class ManagerDto {
    @Schema(description = "Это id менеджера", example = "2")
    private Long id;
    @Schema(description = "Имя менеджера", example = "Петр")
    private String firstName;
    @Schema(description = "Фамилия менеджера", example = "Пушкин")
    private String lastName;
    @Schema(description = "Статус менеджера 1 - активный, 0 - уволенный", example = "1")
    private int status;

}