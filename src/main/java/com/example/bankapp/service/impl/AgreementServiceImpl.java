package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.AccountAgreementDto;
import com.example.bankapp.dtos.AccountDto;
import com.example.bankapp.dtos.AgreementDto;
import com.example.bankapp.dtos.ClientDto;
import com.example.bankapp.entities.*;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.exception.ValidationException;
import com.example.bankapp.mappers.*;
import com.example.bankapp.repository.*;
import com.example.bankapp.service.AgreementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;


    @Override
    public List<AgreementDto> getAll() {
        return agreementRepository.findAll().stream()
                .map(agreementMapper::toDto)
                .toList();
    }

    @Override
    public AgreementDto getById(Long id) {
        Optional<AgreementEntity> optAgreementEntity = agreementRepository.findById(id);
        if (optAgreementEntity.isPresent()) {
            return agreementMapper.toDto(optAgreementEntity.get());
        } else {
            log.info("Agreement id ={} is not found", id);
            throw new NotFoundException("Agreement not found");
        }
    }

    @Override
    public List<AgreementDto> findByClientId(Long id) {
        List<AgreementEntity> agreementEntities = agreementRepository.findByClientId(id);
        if (agreementEntities.isEmpty()) {
            log.info("There is no Agreement for client id ={}", id);
            throw new NotFoundException("Agreement for client id doesn't exist");
        } else {
            return agreementEntities.stream()
                    .map(agreementMapper::toDto)
                    .toList();
        }
    }

    @Override
    public List<AgreementDto> findByAccountId(Long id) {
        List<AgreementEntity> agreementEntities = agreementRepository.findByAccountId(id);
        if (agreementEntities.isEmpty()) {
            log.info("There is no Agreement with account id ={}", id);
            throw new NotFoundException("Agreement for this account id doesn't exist");
        } else {
            return agreementEntities.stream()
                    .map(agreementMapper::toDto)
                    .toList();
        }
    }

    @Override
    public List<AgreementDto> findByProductId(Long id) {
        List<AgreementEntity> agreementEntities = agreementRepository.findByProductId(id);
        if (agreementEntities.isEmpty()) {
            log.info("There is no Agreement for product id ={}", id);
            throw new NotFoundException("Agreement with this product id doesn't exist");
        } else {
            return agreementEntities.stream()
                    .map(agreementMapper::toDto)
                    .toList();
        }
    }

    @Override
    public List<AgreementDto> findByManagerId(Long id) {
        List<AgreementEntity> agreementEntities = agreementRepository.findByManagerId(id);
        if (agreementEntities.isEmpty()) {
            throw new NotFoundException("There are no Agreements with this manager id"
                    + id);
        } else {
            return agreementEntities.stream()
                    .map(agreementMapper::toDto)
                    .toList();
        }
    }

    ///  @Override
    //тот же вопрос про логику создания договора -> счета
    //какой if else должен быть
    /// public AgreementDto createAgreement(AgreementDto agreementDto) {
    // Optional<AgreementEntity> optAgreementEntity = agreementRepository.getById(agreementDto.getId());
    //  if (optAgreementEntity.isEmpty()) {
    ///     AgreementEntity savedAgreement = agreementRepository.save(agreementMapper.toEntity(agreementDto));
    ///    log.info("Created and saved Agreement with ID= {}", savedAgreement.getId());
    ///   return agreementMapper.toDto(savedAgreement);
    //    } else {
    //        log.info("");
    //        throw new ValidationException("Agreement cannot be created");
    //    }
    /// }
    @Transactional
    public AgreementDto createAgreement(AccountAgreementDto accountAgreementDto) {
//clientId V
// accountId V
// productId ManagerId
// InterestRate Status Sum
        AccountEntity accountEntity = accountMapper.toEntity(accountAgreementDto.getAccount());
        Optional<ClientEntity> optClientEntity = clientRepository.findById(accountAgreementDto.getAccount().getClientId());
        accountEntity.setClient(optClientEntity.get());
        AccountEntity savedAccountEntity = accountRepository.saveAndFlush(accountEntity);
        AgreementEntity agreementEntity = agreementMapper.toEntity(accountAgreementDto.getAgreement());
        agreementEntity.setAccount(savedAccountEntity);
       // AgreementEntity savedAgreementEntity = agreementRepository.saveAndFlush(agreementEntity);

        // тут я задаю id product
        ProductEntity productEntity = productMapper.toEntity(accountAgreementDto.getProduct());
        Optional<ProductEntity> optProductEntity = productRepository.findById(accountAgreementDto.getAgreement().getProductId());
        agreementEntity.setProduct(optProductEntity.get());
       // AgreementEntity savedProductInAgreementEntity = agreementRepository.saveAndFlush(agreementEntity);


        // тут я задаю id manager
        ManagerEntity managerEntity = managerMapper.toEntity(accountAgreementDto.getManager());
        Optional<ManagerEntity> optManagerEntity = managerRepository.findById(accountAgreementDto.getAgreement().getManagerId());
        agreementEntity.setManager(optManagerEntity.get());

        // status
        agreementEntity.setStatus(1);

        //interestRate из таблицы продуктов
        int interestRate = accountAgreementDto.getProduct().getInterestRate();
        agreementEntity.setInterestRate(interestRate);


        //не понятно что с sum. из Аккаунт Агримент Дто ее мапить? тогда какой маппер использовать?

        AgreementEntity savedAgreementEntity = agreementRepository.saveAndFlush(agreementEntity);

        return agreementMapper.toDto(agreementEntity);
    }

    @Override
    public AgreementEntity updateAgreement(Long id, AgreementDto clientDto) {
        Optional<AgreementEntity> optAgreementEntity = agreementRepository.findById(id);
        if (optAgreementEntity.isPresent()) {
            AgreementEntity clientEntity = optAgreementEntity.get();
            agreementMapper.updateEntity(clientEntity, clientDto);
            agreementRepository.save(clientEntity);
            log.info("Agreement with ID {} is updated", id);
            return clientEntity;
        }
        throw new NotFoundException("Agreement cannot be updated," + id + "is not found");

    }

    @Override

    //может быть договоры не надо удалять, а только менять их статус?
    public void deleteAgreement(Long id) {
        Optional<AgreementEntity> optAgreementEntity = agreementRepository.findById(id);
        if (optAgreementEntity.isPresent()) {
            agreementRepository.deleteById(id);
            log.info("Agreement id ={} is deleted", id);
            return;
        }
        throw new NotFoundException("Agreement cannot be deleted," + id + "is not found");

    }


}
