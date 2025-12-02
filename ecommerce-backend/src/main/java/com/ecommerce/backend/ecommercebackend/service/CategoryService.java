package com.ecommerce.backend.ecommercebackend.service;

import com.ecommerce.backend.ecommercebackend.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    
    /**
     * Create a new category
     * @param categoryDto Category data
     * @return Created category
     */
    CategoryDto createCategory(CategoryDto categoryDto);
    
    /**
     * Update an existing category
     * @param id Category ID
     * @param categoryDto Updated category data
     * @return Updated category
     */
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    
    /**
     * Get category by ID
     * @param id Category ID
     * @return Category details
     */
    CategoryDto getCategoryById(Long id);
    
    /**
     * Get all categories
     * @return List of all categories
     */
    List<CategoryDto> getAllCategories();
    
    /**
     * Get all active categories
     * @return List of active categories
     */
    List<CategoryDto> getActiveCategories();
    
    /**
     * Get all root categories (categories without parent)
     * @return List of root categories
     */
    List<CategoryDto> getRootCategories();
    
    /**
     * Get subcategories of a parent category
     * @param parentId Parent category ID
     * @return List of subcategories
     */
    List<CategoryDto> getSubcategories(Long parentId);
    
    /**
     * Get category tree (hierarchical structure)
     * @return List of root categories with their children
     */
    List<CategoryDto> getCategoryTree();
    
    /**
     * Delete a category
     * @param id Category ID
     */
    void deleteCategory(Long id);
    
    /**
     * Activate or deactivate a category
     * @param id Category ID
     * @param isActive Active status
     * @return Updated category
     */
    CategoryDto toggleCategoryStatus(Long id, Boolean isActive);
}
