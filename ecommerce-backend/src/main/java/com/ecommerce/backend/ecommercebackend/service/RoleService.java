package com.ecommerce.backend.ecommercebackend.service;

import com.ecommerce.backend.ecommercebackend.entity.Role;

public interface RoleService {
    Role getUserRole();
    Role getAdminRole();
}
