package com.ecommerce.backend.ecommercebackend.controller;

import com.ecommerce.backend.ecommercebackend.dto.CategoryDto;
import com.ecommerce.backend.ecommercebackend.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Get all categories
     */
    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Get all active categories
     */
    @GetMapping("/active")
    public ResponseEntity<?> getActiveCategories() {
        List<CategoryDto> categories = categoryService.getActiveCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Get all root categories (categories without parent)
     */
    @GetMapping("/root")
    public ResponseEntity<?> getRootCategories() {
        List<CategoryDto> categories = categoryService.getRootCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Get category tree (hierarchical structure)
     */
    @GetMapping("/tree")
    public ResponseEntity<?> getCategoryTree() {
        List<CategoryDto> categoryTree = categoryService.getCategoryTree();
        return ResponseEntity.ok(categoryTree);
    }

    /**
     * Get category by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        CategoryDto category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    /**
     * Get subcategories of a parent category
     */
    @GetMapping("/{id}/subcategories")
    public ResponseEntity<?> getSubcategories(@PathVariable Long id) {
        List<CategoryDto> subcategories = categoryService.getSubcategories(id);
        return ResponseEntity.ok(subcategories);
    }

    /**
     * Create new category
     */
    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    /**
     * Update existing category
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDto categoryDto
    ) {
        CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * Toggle category status (activate/deactivate)
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> toggleCategoryStatus(
            @PathVariable Long id,
            @RequestParam Boolean isActive
    ) {
        CategoryDto updatedCategory = categoryService.toggleCategoryStatus(id, isActive);
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * Delete category
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Category deleted successfully");
        return ResponseEntity.ok(response);
    }
}
