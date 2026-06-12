package com.financemanager.financeapp.controller;

import com.financemanager.financeapp.model.User;
import com.financemanager.financeapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users") public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody User user) {
        service.register(user);
        return ResponseEntity.ok("Utilisateur enregistré avec succes");
    }
}
