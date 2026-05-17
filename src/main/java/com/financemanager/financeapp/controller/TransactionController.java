package com.financemanager.financeapp.controller;

import com.financemanager.financeapp.model.Transaction;
import com.financemanager.financeapp.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.apache.logging.log4j.util.LambdaUtil.getAll;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object[]> getAllTransactions() {
        return ResponseEntity.ok(getAll());
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@Valid @RequestBody Transaction transaction) {
        if (transaction.getId() != null) {
            return ResponseEntity.badRequest()
                    .body("ID doit etre null- elle est generée automatiquement.");
        }
        Transaction saved = service.save(transaction);
        return ResponseEntity
                .created(URI.create("/api/transactions/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable long id) {
        Transaction found = service.getById(id);
        if (found != null) {
            return ResponseEntity.ok(found);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Transaction>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(service.searchByTitle(title));
    }
}
