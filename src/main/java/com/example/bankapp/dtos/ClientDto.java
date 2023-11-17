package com.example.bankapp.dtos;

import io.micrometer.observation.ObservationFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Schema(description = "Клиентский ДТО")
public class ClientDto {

    @Schema(description = "Это id", example = "2")
    private Long id;
    @Schema(description = "Статус клиента 1 - у него есть действующие договоры, 0 - действующих договоров нет", example = "1")
    private int status;
    @Schema(description = "Имя клиента", example = "Иван")
    private String firstName;
    @Schema(description = "Фамилия клиента", example = "Иванов")
    private String lastName;
    @Schema(description = "Email клиента, уникальное поле", example = "ivanov@gmail.com")
    private String email;
    @Schema(description = "Адрес клиента", example = "Спб, Невский проспект, 10")
    private String address;
    @Schema(description = "Телефон клиента", example = "89028304490")
    private String phone;

}
