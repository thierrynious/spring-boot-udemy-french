package com.financemanager.financeapp.controller;

import com.financemanager.financeapp.service.async.AsyncDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncDemoService service;

    @GetMapping("/async")
    public String runAsync() throws Exception {
        service.heavyTask();
        return "La tâche asynchrone a été demarrée!!";
    }
}
