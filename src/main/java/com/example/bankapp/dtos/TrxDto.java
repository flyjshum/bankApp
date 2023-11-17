package com.example.bankapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class TrxDto {
  //  private Long id;
    private Long accountId;
    private int type;
  //  private int status;
    private BigDecimal amount;
    private String description;
}
