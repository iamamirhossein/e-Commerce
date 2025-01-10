package com.yerevan.yerevanshops.controller;

import com.yerevan.yerevanshops.model.Category;
import com.yerevan.yerevanshops.response.ApiResponse;
import com.yerevan.yerevanshops.service.category.CategoryService;
import com.yerevan.yerevanshops.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Success!", categories));
        } catch (Exception ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Something went Wrong!", ex.getMessage()));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try {
            Category newCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Success!", newCategory));
        } catch (Exception ex) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Something went Wrong!", ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Success!", category));
        } catch (Exception ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found!", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(new ApiResponse("Success!", null));
        } catch (Exception ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found!", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("Success!", updatedCategory));
        } catch (Exception ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found!", null));
        }
    }

}
