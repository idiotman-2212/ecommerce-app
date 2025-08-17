package com.ecommerce.backend.ecommercebackend.util;

public class Constants {
    
    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;
    
    // JWT
    public static final String JWT_SECRET = "jwt.secret";
    public static final String JWT_EXPIRATION = "jwt.expiration";
    public static final String JWT_REFRESH_EXPIRATION = "jwt.refresh-expiration";
    
    // File Upload
    public static final String UPLOAD_PATH = "file.upload.path";
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    
    // Order Status
    public static final String ORDER_STATUS_PENDING = "PENDING";
    public static final String ORDER_STATUS_CONFIRMED = "CONFIRMED";
    public static final String ORDER_STATUS_SHIPPED = "SHIPPED";
    public static final String ORDER_STATUS_DELIVERED = "DELIVERED";
    public static final String ORDER_STATUS_CANCELLED = "CANCELLED";
    
    // Payment Status
    public static final String PAYMENT_STATUS_PENDING = "PENDING";
    public static final String PAYMENT_STATUS_PAID = "PAID";
    public static final String PAYMENT_STATUS_FAILED = "FAILED";
    public static final String PAYMENT_STATUS_REFUNDED = "REFUNDED";
    
    // User Roles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_MODERATOR = "MODERATOR";
    
    // Error Messages
    public static final String USER_NOT_FOUND = "User not found";
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String ORDER_NOT_FOUND = "Order not found";
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String USERNAME_EXISTS = "Username exists";
    public static final String EMAIL_EXISTS = "Email exists";
    public static final String INSUFFICIENT_STOCK = "Insufficient stock for product";
    
    // Success Messages
    public static final String USER_CREATED = "Create user successfully";
    public static final String USER_UPDATED = "Update user successfully";
    public static final String USER_DELETED = "Delete user successfully";
    public static final String PRODUCT_CREATED = "Create product successfully";
    public static final String PRODUCT_UPDATED = "Update product successfully";
    public static final String PRODUCT_DELETED = "Delete product successfully";
    public static final String ORDER_CREATED = "Create order successfully";
    public static final String ORDER_UPDATED = "Update order successfully";
    public static final String LOGIN_SUCCESS = "Login successful";
    public static final String LOGOUT_SUCCESS = "Logout successful";
}
