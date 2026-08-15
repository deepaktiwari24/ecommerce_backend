package com.deepak.ecommerce_backend.repository;

import com.deepak.ecommerce_backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("all")
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
