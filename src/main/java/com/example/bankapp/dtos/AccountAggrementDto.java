package com.example.bankapp.dtos;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountAggrementDto {

  private AgreementDto agreement;

  private AccountDto account;

}
