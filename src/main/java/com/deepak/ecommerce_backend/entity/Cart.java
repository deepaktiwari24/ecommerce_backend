package com.deepak.ecommerce_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts", indexes = {
        @Index( name = "idx_cart_user_id", columnList = "user_id"),
        @Index( name = "idx_cart_public_id", columnList = "public_id")
})
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "price is required")
    @Min(value = 0)
    @Column(name = "total_price", precision = 12, scale = 2)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItem = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
