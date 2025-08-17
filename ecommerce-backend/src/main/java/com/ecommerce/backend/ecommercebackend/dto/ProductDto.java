package com.ecommerce.backend.ecommercebackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    
    @NotBlank(message = "ProductName cannot be blank")
    @Size(max = 255, message = "ProductName cannot exceed 255 characters")
    private String name;
    
    @NotBlank(message = "Product description cannot be blank")
    private String description;
    
    @NotBlank(message = "SKU cannot be blank")
    @Size(max = 100, message = "SKU cannot exceed 100 characters")
    private String sku;
    
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
    
    @NotNull(message = "Sale price cannot be null")
    @DecimalMin(value = "0.0", message = "Sale price must be greater than or equal to 0")
    private BigDecimal salePrice;
    
    @NotNull(message = "Stock quantity cannot be null")
    @Min(value = 0, message = "Stock quantity must be at least 0")
    private Integer stockQuantity;
    
    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;
    
    private String categoryName;
    private boolean isActive;
    private boolean isFeatured;
    private String brand;
    private String model;
    private String color;
    private String size;
    private BigDecimal weight;
    private String dimensions;
    private List<String> images;
    private List<ProductAttributeDto> attributes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
