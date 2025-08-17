package com.ecommerce.backend.ecommercebackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductAttributeDto {
    private Long id;
    
    @NotBlank(message = "ProductAttribute name cannot be blank")
    private String name;
    
    @NotBlank(message = "ProductAttribute value cannot be blank")
    private String value;
    
    private Long productId;
}
