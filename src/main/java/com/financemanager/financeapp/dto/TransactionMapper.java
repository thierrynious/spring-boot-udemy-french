package com.financemanager.financeapp.dto;

import com.financemanager.financeapp.model.Transaction;

public class TransactionMapper {

    public TransactionDTO toDto(Transaction transaction) {

        TransactionDTO dto = new TransactionDTO();

        dto.setId(transaction.getId());
        dto.setTitle(transaction.getTitle());
        dto.setAmount(transaction.getAmount());
        dto.setDate(transaction.getDate());

        return dto;
    }
}

