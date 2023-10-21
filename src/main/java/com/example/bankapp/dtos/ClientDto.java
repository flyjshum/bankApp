package com.example.bankapp.dtos;

import io.micrometer.observation.ObservationFilter;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ClientDto {
    private Long id;
    private int status;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private Date createdAt;
    private Date updatedAt;
}
