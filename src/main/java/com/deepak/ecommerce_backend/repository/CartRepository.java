package com.deepak.ecommerce_backend.repository;

import com.deepak.ecommerce_backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("all")
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT c.* FROM carts c " +
            "JOIN users u ON c.user_id = u.id " +
            "WHERE u.public_id = UUID_TO_BIN(:publicId)",
            nativeQuery = true)
    Optional<Cart> findByPublicId(@Param("publicId") String publicId);

}
