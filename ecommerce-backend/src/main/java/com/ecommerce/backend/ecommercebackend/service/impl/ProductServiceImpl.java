package com.ecommerce.backend.ecommercebackend.service.impl;

import com.ecommerce.backend.ecommercebackend.dto.ProductDto;
import com.ecommerce.backend.ecommercebackend.entity.Category;
import com.ecommerce.backend.ecommercebackend.entity.Product;
import com.ecommerce.backend.ecommercebackend.mapper.ProductMapper;
import com.ecommerce.backend.ecommercebackend.repository.CategoryRepository;
import com.ecommerce.backend.ecommercebackend.repository.ProductRepository;
import com.ecommerce.backend.ecommercebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProductBySku(String sku) {
        Product product = productRepository.findBySku(sku);
        if (product == null) {
            throw new RuntimeException("Product not found with SKU: " + sku);
        }
        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        // Check if SKU already exists
        if (productRepository.findBySku(productDto.getSku()) != null) {
            throw new RuntimeException("Product with SKU " + productDto.getSku() + " already exists");
        }

        // Check if product name already exists
        if (productRepository.findByName(productDto.getName()) != null) {
            throw new RuntimeException("Product with name " + productDto.getName() + " already exists");
        }

        Product product = productMapper.toEntity(productDto);

        // Set category if provided
        if (productDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + productDto.getCategoryId()));
            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Check if SKU is being changed and if new SKU already exists
        if (!existingProduct.getSku().equals(productDto.getSku())) {
            Product productWithSku = productRepository.findBySku(productDto.getSku());
            if (productWithSku != null && !productWithSku.getId().equals(id)) {
                throw new RuntimeException("Product with SKU " + productDto.getSku() + " already exists");
            }
        }

        // Check if name is being changed and if new name already exists
        if (!existingProduct.getName().equals(productDto.getName())) {
            Product productWithName = productRepository.findByName(productDto.getName());
            if (productWithName != null && !productWithName.getId().equals(id)) {
                throw new RuntimeException("Product with name " + productDto.getName() + " already exists");
            }
        }

        // Update fields
        existingProduct.setName(productDto.getName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setSku(productDto.getSku());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setSalePrice(productDto.getSalePrice());
        existingProduct.setStockQuantity(productDto.getStockQuantity());
        existingProduct.setIsActive(productDto.isActive());
        existingProduct.setIsFeatured(productDto.isFeatured());
        existingProduct.setWeight(productDto.getWeight());
        existingProduct.setDimensions(productDto.getDimensions());

        // Update category if provided
        if (productDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + productDto.getCategoryId()));
            existingProduct.setCategory(category);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}

