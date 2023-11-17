package com.example.bankapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
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

   @Column (name ="name")
   private String name;

    @Column (name ="status")
    private int status;

    @Column (name ="balance")
    private BigDecimal balance;

//    @Column (name ="currency_code")
//    private int currencyCode;

    @CreationTimestamp
    @Column (name ="created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column (name ="updated_at")
    private Date updatedAt;
}


