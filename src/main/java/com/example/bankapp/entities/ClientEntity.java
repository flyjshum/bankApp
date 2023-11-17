package com.example.bankapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true, fetch = FetchType.LAZY)
    //private List<AccountEntity> accounts;

    @Column(name = "status")
    private int status;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @CreationTimestamp
    @Column (name ="created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column (name ="updated_at")
    private Date updatedAt;
}
