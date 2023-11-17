package com.example.bankapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.time.Instant;

@Getter
@Setter
@Entity(name="trx")
public class TrxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Column (name ="type")
    private int type;

    @Column (name ="status")
    private int status;

    @Column (name ="amount")
    private BigDecimal amount;

    @Column (name ="description")
    private String description;

    @CreationTimestamp
    @Column (name ="created_at")
    private Instant createdAt;

}
