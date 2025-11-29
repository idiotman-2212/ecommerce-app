package com.ecommerce.backend.ecommercebackend.service;

import com.ecommerce.backend.ecommercebackend.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    Page<ProductDto> getAllProducts(Pageable pageable);
    ProductDto getProductById(Long id);
    ProductDto getProductBySku(String sku);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
