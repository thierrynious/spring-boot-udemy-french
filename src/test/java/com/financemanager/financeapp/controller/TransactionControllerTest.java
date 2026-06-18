package com.financemanager.financeapp.controller;

import com.financemanager.financeapp.model.Transaction;
import com.financemanager.financeapp.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private TransactionService transactionService;

    @Test
    void should_create_transaction_successfully() throws Exception {

        Transaction tx = new  Transaction();
        tx.setId(1L);
        tx.setTitle("Loyer");
        tx.setAmount(1200.0);
        tx.setDate(LocalDate.of(2023,10,1));

        when(transactionService.save(any())).thenReturn(tx);

        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "title":"Loyer", 
                        "amount":1200.0,
                        "date":"2023-10-01"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Loyer"))
                .andExpect(jsonPath("$.id").value(1));

    }

    @Test
    void should_return_400_when_input_invalid() throws Exception {
        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "title":"",
                        "amount":-5,
                        "date":"2026-12-01"
                        }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_all_transactions() throws Exception {
        Transaction tx = new  Transaction();
        tx.setId(1L);
        tx.setTitle("Loyer");
        tx.setAmount(1000.0);
        tx.setDate(LocalDate.now());

        List<Transaction> dummyList = List.of(tx);

        when(transactionService.getAll())
        .thenReturn(dummyList);

        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Loyer"));
    }
}