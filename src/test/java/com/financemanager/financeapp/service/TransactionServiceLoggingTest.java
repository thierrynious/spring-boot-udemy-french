package com.financemanager.financeapp.service;

import com.financemanager.financeapp.model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.time.LocalDate;
import java.util.logging.Level;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class TransactionServiceLoggingTest {

    @Autowired
    private TransactionService service;

    @Test
    void should_log_info_when_transaction_is_saved(CapturedOutput output) {

        //given
        Transaction tx = new Transaction();

        tx.setTitle("Loyer");
        tx.setAmount(1000.0);
        tx.setDate(LocalDate.now());

        //when
        service.save(tx);

        //then
        assertThat(output.getOut()).contains("Saving transaction Loyer");
    }
}