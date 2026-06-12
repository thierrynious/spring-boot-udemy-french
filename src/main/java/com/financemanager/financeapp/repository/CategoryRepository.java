package com.financemanager.financeapp.repository;

import com.financemanager.financeapp.model.Category;
import com.financemanager.financeapp.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByType(CategoryType type);

    // Query avec JPQL
    @Query("Select c from Category c where c.name Like %:keyword%")
    List<Category> searchByName(@Param("keyword") String keyword);
}


