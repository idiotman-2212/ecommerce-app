package com.ecommerce.backend.ecommercebackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    
    @NotBlank(message = "Category name cannot be blank")
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    private String name;
    
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
    
    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    private String imageUrl;
    
    private Long parentId;
    
    private String parentName;
    
    private Boolean isActive;
    
    private List<CategoryDto> children;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
