package com.ecommerce.backend.ecommercebackend.service;

import com.ecommerce.backend.ecommercebackend.dto.AuthRequestDto;
import com.ecommerce.backend.ecommercebackend.dto.AuthResponseDto;
import com.ecommerce.backend.ecommercebackend.dto.UserDto;

public interface AuthService {
    AuthResponseDto login(AuthRequestDto request);
    AuthResponseDto register(UserDto userDto);
    UserDto getCurrentUser();
}
