package com.ecommerce.backend.ecommercebackend.service.impl;

import com.ecommerce.backend.ecommercebackend.entity.User;
import com.ecommerce.backend.ecommercebackend.repository.UserRepository;
import com.ecommerce.backend.ecommercebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        return "";
    }
}
