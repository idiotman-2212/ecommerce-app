package com.ecommerce.backend.ecommercebackend.repository;

import com.ecommerce.backend.ecommercebackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    List<Category> findByIsActive(Boolean isActive);
    List<Category> findByParentIsNull();
    List<Category> findByParentId(Long parentId);
}
