package com.financemanager.financeapp.controller;

import com.financemanager.financeapp.dto.TransactionDTO;
import com.financemanager.financeapp.model.Transaction;
import com.financemanager.financeapp.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<TransactionDTO>>getPagedTransactions(Pageable pageable) {
        Page<TransactionDTO> result = service.getPaged(pageable)
                .map(this::toDto);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Recuperer toutes transactions",
            description = "Retourne toutes les transactions sous forme de DTO"
    )
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> result = service.getAll()
                .stream()
                .map(this::toDto)
                .toList();

        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Creer une nouvelle transaction",
            description = "Enregistre une nouvelle transaction dans FinanceApp"
    )

    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Transaction créée avec succes"),
            @ApiResponse(responseCode = "400", description = "Requete invalide ou erreur de validation")
    })
    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@Valid @RequestBody TransactionDTO dto) {
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Transaction entity = toEntity(dto);
        Transaction saved = service.save(entity);

        return ResponseEntity.created(URI.create("/api/transactions/" + saved.getId())).body(toDto(saved));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getById(@PathVariable long id) {
        Transaction found = service.getById(id);
        if (found != null) {
            return ResponseEntity.ok(toDto(found));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<TransactionDTO>> searchByTitle(@RequestParam String title) {

        List<TransactionDTO> result = service.searchByTitle(title)
                .stream()
                .map(this::toDto)
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/current-user")
    public  String getCurrentUser() {

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        return """
                User:%s
                Authorities:%s
                """.formatted(
                        auth.getName(),
                auth.getAuthorities(),
                auth.isAuthenticated()
        );
    }

    private TransactionDTO toDto(Transaction transaction) {

        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setTitle(transaction.getTitle());
        dto.setAmount(transaction.getAmount());
        dto.setDate(transaction.getDate());

        return dto;
    }

    private Transaction toEntity(TransactionDTO dto) {

        return new Transaction(
                dto.getTitle(),
                dto.getAmount(),
                dto.getDate()
        );
    }
}
