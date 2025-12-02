package com.ecommerce.backend.ecommercebackend.mapper;

import com.ecommerce.backend.ecommercebackend.dto.CategoryDto;
import com.ecommerce.backend.ecommercebackend.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    /**
     * Convert Category entity to CategoryDto
     * @param category Category entity
     * @return CategoryDto
     */
    public CategoryDto toDto(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setImageUrl(category.getImageUrl());
        dto.setIsActive(category.getIsActive());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdatedAt(category.getUpdatedAt());

        // Map parent category
        if (category.getParent() != null) {
            dto.setParentId(category.getParent().getId());
            dto.setParentName(category.getParent().getName());
        }

        return dto;
    }

    /**
     * Convert Category entity to CategoryDto with children
     * @param category Category entity
     * @return CategoryDto with children
     */
    public CategoryDto toDtoWithChildren(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDto dto = toDto(category);

        // Map children categories
        if (category.getChildren() != null && !category.getChildren().isEmpty()) {
            List<CategoryDto> childrenDtos = category.getChildren().stream()
                    .map(this::toDtoWithChildren)
                    .collect(Collectors.toList());
            dto.setChildren(childrenDtos);
        }

        return dto;
    }

    /**
     * Convert CategoryDto to Category entity
     * @param dto CategoryDto
     * @return Category entity
     */
    public Category toEntity(CategoryDto dto) {
        if (dto == null) {
            return null;
        }

        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setImageUrl(dto.getImageUrl());
        category.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);

        return category;
    }

    /**
     * Convert list of Category entities to list of CategoryDto
     * @param categories List of Category entities
     * @return List of CategoryDto
     */
    public List<CategoryDto> toDtoList(List<Category> categories) {
        if (categories == null) {
            return new ArrayList<>();
        }
        return categories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of Category entities to list of CategoryDto with children
     * @param categories List of Category entities
     * @return List of CategoryDto with children
     */
    public List<CategoryDto> toDtoListWithChildren(List<Category> categories) {
        if (categories == null) {
            return new ArrayList<>();
        }
        return categories.stream()
                .map(this::toDtoWithChildren)
                .collect(Collectors.toList());
    }
}
