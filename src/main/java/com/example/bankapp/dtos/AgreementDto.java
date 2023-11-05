package com.example.bankapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AgreementDto {

    private Long id;

    private Long clientId;
    private Long accountId;
    private Long productId;
    private Long managerId;
    private int interestRate;
    private int status;
    private double sum;

}
