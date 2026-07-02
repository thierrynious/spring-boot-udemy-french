package com.financemanager.financeapp.service;

import com.financemanager.financeapp.model.User;
import com.financemanager.financeapp.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@Validated
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;
    private final PasswordEncoder  passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(@Valid User user) {

        log.info("Debut de l'inscription de l'utilisateur : {}", user.getUsername());

        if(repository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already exists");
        }

        if(repository.findByUsername(user.getUsername()).isPresent()){
            throw new IllegalArgumentException("Username already exists");
        }

        // logique metier
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = repository.save(user);

        log.info("Utilisateur enregistré avec succes avec l'ID {}", saved.getId());

        return saved;
    }
}

