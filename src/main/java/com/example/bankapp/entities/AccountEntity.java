package com.example.bankapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.time.Instant;

@Getter
@Setter
@Entity(name="accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    //@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    //private AgreementEntity agreement;

    @Column (name ="name")
    private String name;

    @Column (name ="type")
    private int type;

    @Column (name ="status")
    private int status;

    @Column (name ="balance")
    private double balance;

    @Column (name ="currency_code")
    private int currencyCode;

    @CreationTimestamp
    @Column (name ="created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column (name ="updated_at")
    private Date updatedAt;
}


