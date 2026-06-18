package com.financemanager.financeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    public Transaction(String loyer, double v, LocalDate now) {
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

    private LocalDateTime createdAt;

    @JsonIgnore
    private String internNote;


}

