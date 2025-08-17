package com.ecommerce.backend.ecommercebackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException ex) {
        log.error("Bad Request: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        log.error("Resource Not Found: {}", ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(UnauthorizeException.class)
    public ResponseEntity<?> handleUnauthorized(UnauthorizeException ex) {
        log.error("Unauthorized Access: {}", ex.getMessage());
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(ForbidenException.class)
    public ResponseEntity<?> handleForbidden(ForbidenException ex) {
        log.error("Forbidden Access: {}", ex.getMessage());
        return ResponseEntity.status(403).body(ex.getMessage());
    }

}
