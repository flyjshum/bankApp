package com.example.bankapp.service;

import com.example.bankapp.dtos.AccountAgreementDto;
import com.example.bankapp.dtos.AgreementDto;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.*;

import java.util.List;

public interface AgreementService {
    List<AgreementDto> getAll();
    AgreementDto getById(Long id);

    List<AgreementDto> findByClientId(Long id);
    List<AgreementDto> findByAccountId(Long id);
    List<AgreementDto> findByProductId(Long id);
    List<AgreementDto> findByManagerId(Long id);

    AgreementDto createAgreement(AccountAgreementDto accountAgreementDto);

    AgreementEntity updateAgreement(Long id, AgreementDto agreementDto);

    void deleteAgreement(Long id);


}
