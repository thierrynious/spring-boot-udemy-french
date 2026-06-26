package com.financemanager.financeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    public Transaction(String title, double amount, LocalDate date) {
        this.title = title;
        this.amount = amount;
        this.date = date;
    }
    public Transaction() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre ne dois pas etre vide.")
    private String title;

    @Positive(message = "Le montant doit etre positif.")
    private double amount;

    @NotNull(message = "La date ne doit pas etre null")
    @PastOrPresent(message = "La date ne doit pas etre dans le futur.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonIgnore
    private String internNote;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
 }

