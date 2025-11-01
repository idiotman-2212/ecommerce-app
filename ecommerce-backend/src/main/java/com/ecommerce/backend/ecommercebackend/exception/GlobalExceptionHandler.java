package com.ecommerce.backend.ecommercebackend.exception;

import com.ecommerce.backend.ecommercebackend.util.ApiResponse;
import com.ecommerce.backend.ecommercebackend.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(BadRequestException ex) {
        log.error("Bad Request: {}", ex.getMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException ex) {
        log.error("Resource Not Found: {}", ex.getMessage());
        return ResponseUtil.error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UnauthorizeException.class)
    public ResponseEntity<ApiResponse<?>> handleUnauthorized(UnauthorizeException ex) {
        log.error("Unauthorized Access: {}", ex.getMessage());
            return ResponseUtil.error(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(ForbidenException.class)
    public ResponseEntity<ApiResponse<?>> handleForbidden(ForbidenException ex) {
        log.error("Forbidden Access: {}", ex.getMessage());
        return ResponseUtil.error(HttpStatus.FORBIDDEN, ex.getMessage());
    }

}
