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
@Entity(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private int status;

    @Column(name = "currency_code")
    private int currencyCode;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "limit_min")
    private BigDecimal limitMin;

    @Column(name = "limit_max")
    private BigDecimal limitMax;

    @CreationTimestamp
    @Column (name ="created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column (name ="updated_at")
    private Date updatedAt;
}

