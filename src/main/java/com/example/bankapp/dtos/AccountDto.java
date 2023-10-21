package com.example.bankapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AccountDto {

    private Long id;
    private Long clientId;
    private String name;
    private int type;
    private int status;
    private double balance;
    private int currencyCode;
    private Date createdAt;
    private Date updatedAt;


}
