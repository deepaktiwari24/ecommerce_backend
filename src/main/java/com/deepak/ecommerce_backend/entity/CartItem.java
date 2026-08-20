package com.deepak.ecommerce_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table( name = "cart_items", indexes =
    @Index(name = "idx_cart_item_public_id", columnList = "public_id"))
@Builder
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "quantity is required")
    @Min(value = 0)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

}
