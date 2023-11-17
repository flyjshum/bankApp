package com.example.bankapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity(name = "agreements")
public class AgreementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @OneToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

   // @Column(name = "account_id")
    //private Long accountId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    //@Column(name = "product_id")
    // private Long productId;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerEntity manager;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "status")
    private int status;

    @Column(name = "sum")
    private BigDecimal sum;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
