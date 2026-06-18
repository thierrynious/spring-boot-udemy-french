package com.financemanager.financeapp.service;

import com.financemanager.financeapp.model.Transaction;
import com.financemanager.financeapp.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Transaction save(Transaction transaction) {
        // Logique métier :
        log.info("Saving transaction {}", transaction.getTitle());
        transaction.setCreatedAt(LocalDateTime.now());

        if (transaction.getAmount() <= 0) {
            log.warn("Montant invalide : {}", transaction.getAmount());
        }

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