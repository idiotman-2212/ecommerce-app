package com.ecommerce.backend.ecommercebackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDto {
    @NotBlank(message = "Username or email cannot be blank")
    private String usernameOrEmail;
    
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
