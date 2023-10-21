package com.example.bankapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private double amount;

    @Column (name ="name")
    private String name;

    @Column (name ="description")
    private int description;

    @Column (name ="created_at")
    private Date createdAt;

}
