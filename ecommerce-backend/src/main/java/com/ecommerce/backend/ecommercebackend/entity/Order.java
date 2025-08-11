package com.ecommerce.backend.ecommercebackend.entity;

import com.ecommerce.backend.ecommercebackend.entity.enumType.OrderStatus;
import com.ecommerce.backend.ecommercebackend.entity.enumType.PaymentStatus;
import com.ecommerce.backend.ecommercebackend.util.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {

    @Column(unique = true, nullable = false, length = 255)
    String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal totalAmount;

    @Column(precision = 10, scale = 2)
    BigDecimal shippingFee = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    BigDecimal taxAmount = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    BigDecimal finalAmount;

    @Enumerated(EnumType.STRING)
    OrderStatus status = OrderStatus.PENDING;

    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(length = 255)
    String paymentMethod;

    @Column(length = 255)
    String ShippingAddress;

    @Column(length = 255)
    String billingAddress;

    @Column(length = 255)
    String shippingMethod;

    String notes;


}
