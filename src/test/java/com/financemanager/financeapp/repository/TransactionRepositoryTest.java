package com.financemanager.financeapp.repository;

import com.financemanager.financeapp.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository repository;

    @Test
    void should_save_and_load_transaction() {

        //given
        Transaction transaction = new Transaction();
        transaction.setTitle("Loyer");
        transaction.setAmount(1000.0);
        transaction.setDate(LocalDate.of(2025, 1, 1));

        //when
        repository.save(transaction);

        List<Transaction> result = repository.findAll();

        //then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Loyer");
        assertThat(result.get(0).getAmount()).isEqualTo(1000.0);
    }

    @Test
    void should_find_by_title_ignore_case(){
        //given
        Transaction transaction = new Transaction();

        transaction.setTitle("Salaire Janvier");
        transaction.setAmount(3000.0);
        transaction.setDate(LocalDate.now());

        repository.save(transaction);

        //when
        List<Transaction> result = repository.findByTitleContainingIgnoreCase("Salaire");

        //then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getTitle()).contains("Salaire");
    }

}