package com.ecommerce.backend.ecommercebackend.util;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
public class ResponseUtil {
    
    public static ResponseEntity<ApiResponse<?>> success(Object data) {
        ApiResponse<?> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Thành công");
        response.setData(data);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
    
    public static ResponseEntity<ApiResponse<?>> success(String message, Object data) {
        ApiResponse<?> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
    
    public static ResponseEntity<ApiResponse<?>> error(HttpStatus status, String message, Object data) {
        ApiResponse<?> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(status).body(response);
    }
    
    public static ResponseEntity<ApiResponse<?>> error(HttpStatus status, String message) {
        return error(status, message, null);
    }
}
