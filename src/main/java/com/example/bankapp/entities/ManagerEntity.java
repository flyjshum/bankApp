package com.example.bankapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity(name = "managers")

public class ManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    private int status;

    @CreationTimestamp
    @Column (name ="created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column (name ="updated_at")
    private Date updatedAt;
}
