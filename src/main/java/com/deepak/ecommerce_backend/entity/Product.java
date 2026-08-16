package com.deepak.ecommerce_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "products",
        indexes = {
                @Index(name = "idx_product_public_id", columnList = "public_id"),
                @Index(name = "idx_product_name", columnList = "name")
        }

)
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false, unique = true, length = 150)
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 20, max = 1000)
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price cannot be negative")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Stock is required")
    @PositiveOrZero(message = "Stock cannot be negative")
    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

    @NotBlank(message = "Image url is required")
    @Column(name = "image_url", nullable = false, length = 512)
    private String imageURl;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToOne      // (fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
