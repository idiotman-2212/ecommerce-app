# Ecommerce Backend API

Backend API hoàn chỉnh cho ứng dụng thương mại điện tử được xây dựng bằng Spring Boot.

## 🚀 Tính năng chính

### 🔐 Authentication & Authorization
- JWT Token authentication
- Role-based access control (ADMIN, USER, MODERATOR)
- Password encryption với BCrypt
- Token refresh mechanism

### 👥 User Management
- Đăng ký, đăng nhập, đăng xuất
- Quản lý profile người dùng
- Đổi mật khẩu
- Phân quyền theo vai trò

### 📦 Product Management
- CRUD operations cho sản phẩm
- Quản lý danh mục sản phẩm
- Tìm kiếm và lọc sản phẩm
- Quản lý tồn kho
- Sản phẩm nổi bật

### 🛒 Order Management
- Tạo và quản lý đơn hàng
- Trạng thái đơn hàng (PENDING → DELIVERED)
- Quản lý thanh toán
- Chi tiết đơn hàng

### 🎯 Tính năng khác
- Giỏ hàng
- Danh sách yêu thích
- Đánh giá sản phẩm
- Mã giảm giá
- Thông báo
- File upload

## 🛠️ Công nghệ sử dụng

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Security**
- **Spring Data JPA**
- **MySQL 8.0**
- **JWT (JSON Web Tokens)**
- **Maven**
- **Lombok**

## 📋 Yêu cầu hệ thống

- Java 21+
- MySQL 8.0+
- Maven 3.6+

## 🚀 Cài đặt và chạy

### 1. Clone repository
```bash
git clone <repository-url>
cd ecommerce-backend
```

### 2. Cấu hình database
- Tạo database MySQL: `ecommerce_db`
- Chạy script SQL: `database/ecommerce_db.sql`

### 3. Cấu hình application.properties
Chỉnh sửa file `src/main/resources/application.properties`:
```properties
# Database
spring.datasource.username=your_username
spring.datasource.password=your_password

# JWT (Thay đổi secret key cho production)
jwt.secret=your-secret-key-here-make-it-very-long-and-secure

# Email (Tùy chọn)
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

### 4. Chạy ứng dụng
```bash
mvn spring-boot:run
```

Ứng dụng sẽ chạy tại: `http://localhost:8080/api`

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication Endpoints

#### Đăng nhập
```http
POST /auth/login
Content-Type: application/json

{
  "usernameOrEmail": "admin",
  "password": "admin123"
}
```

#### Đăng ký
```http
POST /auth/register
Content-Type: application/json

{
  "username": "newuser",
  "email": "user@example.com",
  "password": "password123",
  "firstName": "New",
  "lastName": "User"
}
```

#### Refresh Token
```http
POST /auth/refresh?refreshToken=your-refresh-token
```

### User Management Endpoints

#### Lấy danh sách users (Admin only)
```http
GET /users?page=0&size=20&sortBy=id&sortDir=desc
Authorization: Bearer your-jwt-token
```

#### Tạo user mới (Admin only)
```http
POST /users
Authorization: Bearer your-jwt-token
Content-Type: application/json

{
  "username": "newuser",
  "email": "user@example.com",
  "password": "password123",
  "firstName": "New",
  "lastName": "User",
  "roles": ["USER"]
}
```

#### Cập nhật user
```http
PUT /users/{id}
Authorization: Bearer your-jwt-token
Content-Type: application/json

{
  "firstName": "Updated",
  "lastName": "Name"
}
```

### Product Endpoints

#### Lấy danh sách sản phẩm
```http
GET /products?page=0&size=20
```

#### Lấy sản phẩm theo ID
```http
GET /products/{id}
```

#### Tìm kiếm sản phẩm
```http
GET /products/search?keyword=iphone&page=0&size=20
```

#### Tạo sản phẩm mới (Admin only)
```http
POST /products
Authorization: Bearer your-jwt-token
Content-Type: application/json

{
  "name": "iPhone 15 Pro",
  "description": "iPhone 15 Pro với chip A17 Pro",
  "sku": "IPHONE-15-PRO",
  "price": 25000000,
  "salePrice": 23000000,
  "stockQuantity": 50,
  "categoryId": 1,
  "brand": "Apple",
  "model": "iPhone 15 Pro"
}
```

## 🔐 Security

### JWT Token
- Access Token: 24 giờ
- Refresh Token: 7 ngày
- Algorithm: HS256

### Roles & Permissions
- **ADMIN**: Toàn quyền truy cập
- **USER**: Truy cập hạn chế
- **MODERATOR**: Quyền trung gian

### CORS Configuration
- Allowed Origins: `http://localhost:3000`, `http://localhost:4200`
- Allowed Methods: GET, POST, PUT, DELETE, OPTIONS
- Allowed Headers: *

## 📊 Database Schema

### Bảng chính
- `users` - Người dùng
- `roles` - Vai trò
- `products` - Sản phẩm
- `categories` - Danh mục
- `orders` - Đơn hàng
- `order_items` - Chi tiết đơn hàng
- `shopping_cart` - Giỏ hàng
- `reviews` - Đánh giá
- `coupons` - Mã giảm giá
- `wishlist` - Danh sách yêu thích

## 🧪 Testing

### Chạy tests
```bash
mvn test
```

### Test với Postman
Import file collection vào Postman để test các API endpoints.

## 📝 Logging

- Log level: INFO (production), DEBUG (development)
- Log file: `logs/application.log`
- Structured logging với SLF4J

## 🔧 Configuration

### Environment Variables
```bash
export SPRING_PROFILES_ACTIVE=prod
export DB_USERNAME=your_db_username
export DB_PASSWORD=your_db_password
export JWT_SECRET=your_jwt_secret
```

### Profiles
- `dev`: Development environment
- `prod`: Production environment
- `test`: Testing environment

## 🚀 Deployment

### Docker
```bash
# Build image
docker build -t ecommerce-backend .

# Run container
docker run -p 8080:8080 ecommerce-backend
```

### Production
1. Cấu hình database production
2. Cập nhật JWT secret
3. Cấu hình email service
4. Setup reverse proxy (Nginx)
5. Configure SSL certificate

## 📈 Monitoring

### Health Check
```http
GET /actuator/health
```

### Metrics
```http
GET /actuator/metrics
```

## 🤝 Contributing

1. Fork repository
2. Tạo feature branch
3. Commit changes
4. Push to branch
5. Tạo Pull Request

## 📄 License

MIT License - xem file [LICENSE](LICENSE) để biết thêm chi tiết.

## 📞 Support

- Email: support@ecommerce.com
- Documentation: [API Docs](docs/api.md)
- Issues: [GitHub Issues](https://github.com/your-repo/issues)

## 🔄 Changelog

### v1.0.0 (2024-01-01)
- ✅ JWT Authentication
- ✅ User Management
- ✅ Product Management
- ✅ Order Management
- ✅ Role-based Authorization
- ✅ Exception Handling
- ✅ Data Validation
- ✅ CORS Configuration
- ✅ Logging
- ✅ Health Checks

---

**Backend API đã sẵn sàng để tích hợp với frontend!** 🎉


