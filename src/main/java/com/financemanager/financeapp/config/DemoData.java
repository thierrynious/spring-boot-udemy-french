package com.financemanager.financeapp.config;

import com.financemanager.financeapp.model.Category;
import com.financemanager.financeapp.model.CategoryType;
import com.financemanager.financeapp.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoData {

    @Bean
    CommandLineRunner loadSampleData(CategoryRepository categoryRepository){
        return args -> {
            Category c = new Category();
            c.setName("Alimentation");
            c.setType(CategoryType.DEPENSE);
            categoryRepository.save(c);
            System.out.println("Categorie exemple enregistrée");
        };
    }

}
