package com.ecommerce.backend.ecommercebackend.entity;

import com.ecommerce.backend.ecommercebackend.entity.enumType.DiscountType;
import com.ecommerce.backend.ecommercebackend.util.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "coupons")
public class Coupon extends BaseEntity {

    @Column(unique = true, nullable = false, length = 50)
    String code;

    @Column(nullable = false, length = 100)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false, length = 20)
    DiscountType discountType;

    @Column(name = "discount_value", nullable = false, precision = 10, scale = 2)
    BigDecimal discountValue;

    @Column(name = "min_order_amount", precision = 10, scale = 2)
    BigDecimal minOrderAmount = BigDecimal.ZERO;

    @Column(name = "max_discount_amount", precision = 10, scale = 2)
    BigDecimal maxDiscountAmount;

    @Column(name = "usage_limit")
    Integer usageLimit;

    @Column(name = "used_count")
    Integer usedCount = 0;

    @Column(name = "is_active")
    Boolean isActive = true;

    @Column(name = "valid_from", nullable = false)
    LocalDateTime validFrom;

    @Column(name = "valid_until", nullable = false)
    LocalDateTime validUntil;

}
