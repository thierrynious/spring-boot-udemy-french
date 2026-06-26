package com.financemanager.financeapp.service;

import com.financemanager.financeapp.config.AppProperties;
import com.financemanager.financeapp.model.Transaction;
import com.financemanager.financeapp.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final AppProperties appProperties;
    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository repository, AppProperties appProperties) {
        this.repository = repository;
        this.appProperties = appProperties;
    }

    // Exécuté automatiquement au demarrage
    @PostConstruct
    public void init() {
        log.info("{}démarrée | Transactions max: {} | Devise: {}",
                appProperties.getName(),
                appProperties.getMaxTransactions(),
                appProperties.getDefaultCurrency());
    }

    public Page<Transaction> getPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Transaction save(Transaction transaction) {
        // Logique métier :
        log.info("Saving transaction {}", transaction.getTitle());
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