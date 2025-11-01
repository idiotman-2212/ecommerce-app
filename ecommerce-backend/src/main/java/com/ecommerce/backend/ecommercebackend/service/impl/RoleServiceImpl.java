package com.ecommerce.backend.ecommercebackend.service.impl;

import com.ecommerce.backend.ecommercebackend.entity.Role;
import com.ecommerce.backend.ecommercebackend.repository.RoleRepository;
import com.ecommerce.backend.ecommercebackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getUserRole() {
        return roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));
    }

    @Override
    public Role getAdminRole() {
        return null;
    }
}
