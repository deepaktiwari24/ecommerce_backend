package com.deepak.ecommerce_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", nullable = false, unique = true, updatable = false)
    private UUID publicId;

    @NotNull(message = "price is required")
    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private CartItem cartItem;

    @OneToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
