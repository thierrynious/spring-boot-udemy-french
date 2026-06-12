package com.financemanager.financeapp.service;

import com.financemanager.financeapp.model.Category;
import com.financemanager.financeapp.model.CategoryType;
import com.financemanager.financeapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // create
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // read - toutes les categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // read - categorie par ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElse(null);
    }

    // update
    public Category updateCategory(Long id, Category updatedCategory) {

        Category existingCategory = getCategoryById(id);

        if (existingCategory == null) {
            return null;
        }

        existingCategory.setName(updatedCategory.getName());
        existingCategory.setType(updatedCategory.getType());

        return categoryRepository.save(existingCategory);
    }

    // delete
    public boolean deleteCategory(Long id) {

        if (!categoryRepository.existsById(id)) {
            return false;
        }

        categoryRepository.deleteById(id);

        return true;
    }

    // read - categories par type
    public List<Category> getByType(CategoryType type) {
        return categoryRepository.findByType(type);
    }

    // read - recherche par nom
    public List<Category> searchByName(String keyword) {
        return categoryRepository.searchByName(keyword);
    }
}