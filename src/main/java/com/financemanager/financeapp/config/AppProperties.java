package com.financemanager.financeapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String name;
    private String defaultCurrency;
    private int maxTransactions;
}
