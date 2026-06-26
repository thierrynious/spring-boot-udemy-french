package com.financemanager.financeapp.service.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class AsyncDemoService {

    @Async
    public CompletableFuture<String> heavyTask() throws InterruptedException{

        log.info("Démarrage de la tâche dans le thread: {}", Thread.currentThread().getName());
        Thread.sleep(3000);
        log.info("Tâche terminée!!");
        return CompletableFuture.completedFuture("Termninée");
    }
}
