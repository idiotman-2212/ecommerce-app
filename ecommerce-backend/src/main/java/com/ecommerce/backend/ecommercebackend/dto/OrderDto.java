package com.ecommerce.backend.ecommercebackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    
    @NotNull(message = "User ID cannot be null")
    private Long userId;
    
    @NotBlank(message = "Shipping address cannot be empty")
    private String shippingAddress;
    
    @NotBlank(message = "Shipping name cannot be empty")
    private String shippingCity;
    
    @NotBlank(message = "Shipping state cannot be empty")
    private String shippingCountry;
    
    @NotBlank(message = "Shipping postal code cannot be empty")
    private String shippingPostalCode;
    
    @NotBlank(message = "Shipping phone cannot be empty")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Phone number must be 10 or 11 digits")
    private String shippingPhone;
    
    @NotNull(message = "Total amount cannot be null")
    @DecimalMin(value = "0.0", message = "Total amount must be greater than or equal to 0")
    private BigDecimal totalAmount;
    
    @NotNull(message = "Shipping fee cannot be null")
    @DecimalMin(value = "0.0", message = "Shipping fee must be greater than or equal to 0")
    private BigDecimal shippingFee;
    
    @NotNull(message = "Tax amount cannot be null")
    @DecimalMin(value = "0.0", message = "Tax amount must be greater than or equal to 0")
    private BigDecimal taxAmount;
    
    @NotNull(message = "Discount amount cannot be null")
    @DecimalMin(value = "0.0", message = "Discount amount must be greater than or equal to 0")
    private BigDecimal discountAmount;
    
    @NotNull(message = "Final amount cannot be null")
    @DecimalMin(value = "0.0", message = "Final amount must be greater than or equal to 0")
    private BigDecimal finalAmount;
    
    private String status;
    private String paymentStatus;
    private String paymentMethod;
    private String shippingMethod;
    private String couponCode;
    private String notes;
    private List<OrderItemDto> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
