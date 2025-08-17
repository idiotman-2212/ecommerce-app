package com.ecommerce.backend.ecommercebackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Long id;
    
    @NotNull(message = "Order ID cannot be null")
    private Long orderId;
    
    @NotNull(message = "Product ID cannot be null")
    private Long productId;
    
    @NotBlank(message = "Product name cannot be blank")
    private String productName;
    
    @NotBlank(message = "SKU cannot be blank")
    private String productSku;
    
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    @NotNull(message = "Unit price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than 0")
    private BigDecimal unitPrice;
    
    @NotNull(message = "Total price cannot be null")
    @DecimalMin(value = "0.0", message = "Total price must be greater than or equal to 0")
    private BigDecimal totalPrice;
    
    private String productImage;
}
