package com.deepak.ecommerce_backend.repository;

import com.deepak.ecommerce_backend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@SuppressWarnings("all")
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


}
