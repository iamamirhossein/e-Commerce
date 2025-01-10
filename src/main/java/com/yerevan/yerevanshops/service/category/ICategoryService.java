package com.yerevan.yerevanshops.service.category;

import com.yerevan.yerevanshops.model.Category;

import java.util.List;

public interface ICategoryService {
    Category addCategory(Category category);
    void deleteCategory(Long id);
    Category updateCategory(Category category, Long id);
    List<Category> getAllCategories();
    Category getCategoryByName(String name);
    Category getCategoryById(Long id);
}
