package com.ecommerce.backend.ecommercebackend.service.impl;

import com.ecommerce.backend.ecommercebackend.entity.Role;
import com.ecommerce.backend.ecommercebackend.entity.User;
import com.ecommerce.backend.ecommercebackend.exception.BadRequestException;
import com.ecommerce.backend.ecommercebackend.repository.RoleRepository;
import com.ecommerce.backend.ecommercebackend.repository.UserRepository;
import com.ecommerce.backend.ecommercebackend.service.RoleService;
import com.ecommerce.backend.ecommercebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new BadRequestException("Username already exists");
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new BadRequestException("Email already exists");
        }
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsActive(true);
        if(user.getRoles() == null || user.getRoles().isEmpty()) {
            Role userRole = roleService.getUserRole();
            user.setRoles(Set.of(userRole));
        } else {
            Set<Role> roles = user.getRoles().stream()
                    .map(r -> roleRepository.findByName(r.getName())
                            .orElseThrow(() -> new BadRequestException("Role not found: " + r.getName())))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found with id: " + id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles() == null || user.getRoles().isEmpty()) {
            Role userRole = roleService.getUserRole();
            existingUser.setRoles(Set.of(userRole));
        } else {
            Set<Role> resolved = user.getRoles().stream()
                    .map(r -> roleRepository.findByName(r.getName())
                            .orElseThrow(() -> new BadRequestException("Role not found: " + r.getName())))
                    .collect(Collectors.toSet());
            existingUser.setRoles(resolved);
        }
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public String deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            throw new BadRequestException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        return "Deleted user with id: " + id;
    }
}
