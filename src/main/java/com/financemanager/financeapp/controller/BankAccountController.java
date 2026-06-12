package com.financemanager.financeapp.controller;

import com.financemanager.financeapp.model.BankAccount;
import com.financemanager.financeapp.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BankAccount> create() {
        return ResponseEntity.ok(service.create());
    }

    @PostMapping("/{id}/transaction")
    public ResponseEntity<BankAccount> transaction(
            @PathVariable Long id,
            @RequestParam double amount
    ) {
        return ResponseEntity.ok(
                service.applyTransaction(id, amount)
        );
    }

    @GetMapping("/{id}")
    public BankAccount getById(@PathVariable Long id) {
        return service.getById(id);
    }
}