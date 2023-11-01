package com.example.bankapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private int status;
    private int currencyCode;
    private int interestRate;
    private double limit;

}
