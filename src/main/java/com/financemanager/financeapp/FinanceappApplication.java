package com.financemanager.financeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableJpaAuditing
public class FinanceappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceappApplication.class, args);
	}
}
