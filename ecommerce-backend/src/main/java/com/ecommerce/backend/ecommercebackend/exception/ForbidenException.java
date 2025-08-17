package com.ecommerce.backend.ecommercebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbidenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ForbidenException(String message) {
        super(message);
    }

    public ForbidenException(String message, Throwable cause) {
        super(message, cause);
    }
}
