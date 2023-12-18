package com.example.bankapp.job;

import com.example.bankapp.dtos.AgreementDto;
import com.example.bankapp.entities.AccountEntity;
import com.example.bankapp.entities.AgreementEntity;
import com.example.bankapp.entities.ProductEntity;
import com.example.bankapp.entities.TrxEntity;
import com.example.bankapp.enums.Status;
import com.example.bankapp.enums.TrxType;
import com.example.bankapp.mappers.AgreementMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.AgreementRepository;
import com.example.bankapp.repository.TrxRepository;
import com.example.bankapp.service.AccountService;
import com.example.bankapp.service.AgreementService;
import jakarta.transaction.Transactional;
import liquibase.integration.spring.SpringResourceAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class InterestRatePayment {
    private final AgreementService agreementService;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final AgreementMapper agreementMapper;
    private final TrxRepository trxRepository;

    @Scheduled(cron = "0 0 L * * ?")
    @Transactional
    public void run() {
        try { //to Entity
            List<AgreementDto> agreementDtos = agreementService.findAllActive();
            for (int i = 0; i < agreementDtos.size(); i++) {
                AgreementDto agreementDto = agreementDtos.get(i);
                Optional<AccountEntity> optAccountEntity = accountRepository.findById(agreementDto.getAccountId());
                AccountEntity accountEntity = optAccountEntity.get();
                BigDecimal currentBalance = accountEntity.getBalance();
                BigDecimal interestRate = currentBalance.multiply(BigDecimal.valueOf(agreementMapper.toEntity(agreementDto).getInterestRate() / 100 / 12))
                        .multiply(agreementMapper.toEntity(agreementDto).getProduct().getPaymentFrequency());
                TrxEntity trxEntity = new TrxEntity();
                trxEntity.setAccount(accountEntity);
                trxEntity.setStatus(Status.ACTIVE);
                trxEntity.setType(TrxType.DEBIT);
                trxEntity.setDescription("Interest Rate Payout");
                trxEntity.setAmount(interestRate);
                TrxEntity savedTrxEntity = trxRepository.saveAndFlush(trxEntity);

                accountEntity.setBalance(currentBalance.add(interestRate));
                AccountEntity savedAccountEntity = accountRepository.saveAndFlush(accountEntity);

            }

        } catch (Exception e) {
            log.error("Error during interest rate payout", e);
        }
    }
}
