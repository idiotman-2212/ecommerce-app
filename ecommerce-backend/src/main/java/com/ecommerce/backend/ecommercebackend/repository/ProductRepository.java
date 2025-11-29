package com.ecommerce.backend.ecommercebackend.repository;

import com.ecommerce.backend.ecommercebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findBySku(String sku);
    Product findByName(String name);
}
