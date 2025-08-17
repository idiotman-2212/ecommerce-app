package com.ecommerce.backend.ecommercebackend.util;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private Object data;
    private LocalDateTime timestamp;
}
