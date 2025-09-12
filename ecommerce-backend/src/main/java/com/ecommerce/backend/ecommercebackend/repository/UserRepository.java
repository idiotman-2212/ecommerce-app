package com.ecommerce.backend.ecommercebackend.repository;

import com.ecommerce.backend.ecommercebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword% OR u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword%")
    List<User> findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            @Param("keyword") String keyword);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<User> findByRolesName(@Param("roleName") String roleName);

    List<User> findByIsActive(Boolean isActive);

}
