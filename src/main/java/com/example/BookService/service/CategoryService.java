package com.example.BookService.service;

import com.example.BookService.model.Category;
import com.example.BookService.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getCategory (String categoryName){
        Category category = categoryRepository.findFirstByName(categoryName);
        if (category != null){
            return category;
        }
        category = new Category();
        category.setName(categoryName);
        return categoryRepository.save(category);
    }
}
