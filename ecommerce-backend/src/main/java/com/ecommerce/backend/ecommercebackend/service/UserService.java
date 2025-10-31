package com.ecommerce.backend.ecommercebackend.service;

import com.ecommerce.backend.ecommercebackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    String deleteUser(Long id);
}
