package com.ecommerce.backend.ecommercebackend.controller;

import com.ecommerce.backend.ecommercebackend.dto.AuthRequestDto;
import com.ecommerce.backend.ecommercebackend.dto.AuthResponseDto;
import com.ecommerce.backend.ecommercebackend.dto.UserDto;
import com.ecommerce.backend.ecommercebackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.register(userDto));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }
}
