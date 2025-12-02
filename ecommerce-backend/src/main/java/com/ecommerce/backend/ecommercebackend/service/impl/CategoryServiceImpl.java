package com.ecommerce.backend.ecommercebackend.service.impl;

import com.ecommerce.backend.ecommercebackend.dto.CategoryDto;
import com.ecommerce.backend.ecommercebackend.entity.Category;
import com.ecommerce.backend.ecommercebackend.exception.BadRequestException;
import com.ecommerce.backend.ecommercebackend.exception.ResourceNotFoundException;
import com.ecommerce.backend.ecommercebackend.mapper.CategoryMapper;
import com.ecommerce.backend.ecommercebackend.repository.CategoryRepository;
import com.ecommerce.backend.ecommercebackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) {
        // Check if category name already exists
        Category existingCategory = categoryRepository.findByName(categoryDto.getName());
        if (existingCategory != null) {
            throw new BadRequestException("Category with name '" + categoryDto.getName() + "' already exists");
        }

        Category category = categoryMapper.toEntity(categoryDto);

        // Set parent category if provided
        if (categoryDto.getParentId() != null) {
            Category parent = categoryRepository.findById(categoryDto.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found with id: " + categoryDto.getParentId()));
            category.setParent(parent);
        }

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        // Check if name is being changed and if new name already exists
        if (!existingCategory.getName().equals(categoryDto.getName())) {
            Category categoryWithName = categoryRepository.findByName(categoryDto.getName());
            if (categoryWithName != null && !categoryWithName.getId().equals(id)) {
                throw new BadRequestException("Category with name '" + categoryDto.getName() + "' already exists");
            }
        }

        // Update fields
        existingCategory.setName(categoryDto.getName());
        existingCategory.setDescription(categoryDto.getDescription());
        existingCategory.setImageUrl(categoryDto.getImageUrl());
        
        if (categoryDto.getIsActive() != null) {
            existingCategory.setIsActive(categoryDto.getIsActive());
        }

        // Update parent category if provided
        if (categoryDto.getParentId() != null) {
            // Check for circular reference
            if (categoryDto.getParentId().equals(id)) {
                throw new BadRequestException("Category cannot be its own parent");
            }
            
            Category parent = categoryRepository.findById(categoryDto.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found with id: " + categoryDto.getParentId()));
            
            // Check if the parent is a descendant of this category
            if (isDescendant(existingCategory, parent)) {
                throw new BadRequestException("Cannot set parent to a descendant category");
            }
            
            existingCategory.setParent(parent);
        } else {
            existingCategory.setParent(null);
        }

        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDtoList(categories);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getActiveCategories() {
        List<Category> categories = categoryRepository.findByIsActive(true);
        return categoryMapper.toDtoList(categories);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getRootCategories() {
        List<Category> rootCategories = categoryRepository.findByParentIsNull();
        return categoryMapper.toDtoList(rootCategories);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getSubcategories(Long parentId) {
        // Verify parent category exists
        categoryRepository.findById(parentId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent category not found with id: " + parentId));
        
        List<Category> subcategories = categoryRepository.findByParentId(parentId);
        return categoryMapper.toDtoList(subcategories);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getCategoryTree() {
        List<Category> rootCategories = categoryRepository.findByParentIsNull();
        return categoryMapper.toDtoListWithChildren(rootCategories);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        // Check if category has children
        List<Category> children = categoryRepository.findByParentId(id);
        if (!children.isEmpty()) {
            throw new BadRequestException("Cannot delete category with subcategories. Please delete or reassign subcategories first.");
        }

        // Note: In a production system, you might also want to check if there are products
        // associated with this category before deleting
        
        categoryRepository.delete(category);
    }

    @Override
    @Transactional
    public CategoryDto toggleCategoryStatus(Long id, Boolean isActive) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        
        category.setIsActive(isActive);
        Category updatedCategory = categoryRepository.save(category);
        
        return categoryMapper.toDto(updatedCategory);
    }

    /**
     * Helper method to check if a category is a descendant of another category
     * @param ancestor Potential ancestor category
     * @param descendant Potential descendant category
     * @return true if descendant is a descendant of ancestor
     */
    private boolean isDescendant(Category ancestor, Category descendant) {
        if (descendant == null) {
            return false;
        }
        
        Category current = descendant;
        while (current != null) {
            if (current.getId().equals(ancestor.getId())) {
                return true;
            }
            current = current.getParent();
        }
        
        return false;
    }

}
