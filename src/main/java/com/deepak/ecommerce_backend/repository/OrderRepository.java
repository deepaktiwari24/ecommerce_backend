package com.deepak.ecommerce_backend.repository;

import com.deepak.ecommerce_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("all")
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
