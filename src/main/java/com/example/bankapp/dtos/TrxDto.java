package com.example.bankapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TrxDto {
    private Long id;
    private int accountId;
    private int type;
    private int status;
    private double amount;
    private String description;
    private Date createdAt;
}
