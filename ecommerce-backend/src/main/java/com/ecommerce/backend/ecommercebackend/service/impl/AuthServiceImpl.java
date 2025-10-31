package com.ecommerce.backend.ecommercebackend.service.impl;

import com.ecommerce.backend.ecommercebackend.dto.AuthRequestDto;
import com.ecommerce.backend.ecommercebackend.dto.AuthResponseDto;
import com.ecommerce.backend.ecommercebackend.dto.UserDto;
import com.ecommerce.backend.ecommercebackend.entity.Role;
import com.ecommerce.backend.ecommercebackend.entity.User;
import com.ecommerce.backend.ecommercebackend.exception.BadRequestException;
import com.ecommerce.backend.ecommercebackend.exception.ResourceNotFoundException;
import com.ecommerce.backend.ecommercebackend.repository.RoleRepository;
import com.ecommerce.backend.ecommercebackend.repository.UserRepository;
import com.ecommerce.backend.ecommercebackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.backend.ecommercebackend.service.AuthService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponseDto login(AuthRequestDto request) {
        String username = request.getUsernameOrEmail();
        User user = userRepository.findByUsername(username)
                .or(() -> userRepository.findByEmail(username))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(user);
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setTokenType("Bearer");
        response.setExpiresIn(3600L); // hoặc lấy từ JWTUtil nếu có
        UserDto userDto = mapUserToUserDto(user);
        response.setUser(userDto);
        return response;
    }

    @Override
    public AuthResponseDto register(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new BadRequestException("Username already exists");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role USER not found"));
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setCountry(userDto.getCountry());
        user.setPostalCode(userDto.getPostalCode());
        user.setRoles(Set.of(userRole));
        userRepository.save(user);
        String token = jwtUtil.generateToken(user);
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setTokenType("Bearer");
        response.setExpiresIn(3600L);
        response.setUser(mapUserToUserDto(user));
        return response;
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadRequestException("No authenticated user");
        }
        System.out.println("hello");
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapUserToUserDto(user);
    }

    private UserDto mapUserToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setCountry(user.getCountry());
        dto.setPostalCode(user.getPostalCode());
        dto.setActive(Boolean.TRUE.equals(user.getIsActive()));
        dto.setRoles(user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet()));
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}
