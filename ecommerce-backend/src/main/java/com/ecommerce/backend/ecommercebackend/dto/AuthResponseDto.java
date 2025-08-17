package com.ecommerce.backend.ecommercebackend.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private String refreshToken;
    private String tokenType = "Bearer";
    private Long expiresIn;
    private UserDto user;
}
