package com.financemanager.financeapp.repository;

import com.financemanager.financeapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTitleContainingIgnoreCase(String title);

    List<Transaction> findByDateBetween(LocalDate start, LocalDate end);

    List<Transaction> findByAmountGreaterThan(double amount);
}





