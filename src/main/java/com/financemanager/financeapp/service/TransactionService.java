package com.financemanager.financeapp.service;

import com.financemanager.financeapp.model.Transaction;
import com.financemanager.financeapp.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Transaction save(Transaction transaction) {

        // Logique métier :
        // ajouter automatiquement la date et l'heure de création
        transaction.setCreatedAt(LocalDateTime.now());

        return repository.save(transaction);
    }

    public Transaction getById(Long id) {

        return repository.findById(id)
                .orElse(null);
    }

    public List<Transaction> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
}