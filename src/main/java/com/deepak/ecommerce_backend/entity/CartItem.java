package com.deepak.ecommerce_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table( name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", nullable = false, unique = true, updatable = false)
    private UUID publicId;

    @NotNull(message = "quantity is required")
    private Integer quantity;

    @NotNull(message = "price is required")
    private Double price;

    @OneToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

}
