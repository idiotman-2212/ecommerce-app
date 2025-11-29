package com.ecommerce.backend.ecommercebackend.mapper;

import com.ecommerce.backend.ecommercebackend.dto.ProductAttributeDto;
import com.ecommerce.backend.ecommercebackend.dto.ProductDto;
import com.ecommerce.backend.ecommercebackend.entity.Product;
import com.ecommerce.backend.ecommercebackend.entity.ProductAttribute;
import com.ecommerce.backend.ecommercebackend.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setSku(product.getSku());
        dto.setPrice(product.getPrice());
        dto.setSalePrice(product.getSalePrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setActive(product.getIsActive());
        dto.setFeatured(product.getIsFeatured());
        dto.setWeight(product.getWeight());
        dto.setDimensions(product.getDimensions());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());

        // Map category
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }

        // Map images
        if (product.getImages() != null) {
            List<String> imageUrls = product.getImages().stream()
                    .map(ProductImage::getImageUrl)
                    .collect(Collectors.toList());
            dto.setImages(imageUrls);
        }

        // Map attributes
        if (product.getAttributes() != null) {
            List<ProductAttributeDto> attributeDtos = product.getAttributes().stream()
                    .map(this::attributeToDto)
                    .collect(Collectors.toList());
            dto.setAttributes(attributeDtos);
        }

        return dto;
    }

    public Product toEntity(ProductDto dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setSku(dto.getSku());
        product.setPrice(dto.getPrice());
        product.setSalePrice(dto.getSalePrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setIsActive(dto.isActive());
        product.setIsFeatured(dto.isFeatured());
        product.setWeight(dto.getWeight());
        product.setDimensions(dto.getDimensions());

        return product;
    }

    public List<ProductDto> toDtoList(List<Product> products) {
        if (products == null) {
            return new ArrayList<>();
        }
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ProductAttributeDto attributeToDto(ProductAttribute attribute) {
        if (attribute == null) {
            return null;
        }

        ProductAttributeDto dto = new ProductAttributeDto();
        dto.setId(attribute.getId());
        dto.setName(attribute.getAttributeName());
        dto.setValue(attribute.getAttributeValue());
        if (attribute.getProduct() != null) {
            dto.setProductId(attribute.getProduct().getId());
        }

        return dto;
    }

}
