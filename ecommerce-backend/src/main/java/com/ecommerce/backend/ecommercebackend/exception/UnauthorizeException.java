package com.ecommerce.backend.ecommercebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizeException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UnauthorizeException(String message) {
        super(message);
    }

    public UnauthorizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
