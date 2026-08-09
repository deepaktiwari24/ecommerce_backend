package com.deepak.ecommerce_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "products",
        indexes = {
        @Index(name = "idx_product_public_id" , columnList = "public_id"),
        @Index(name = "idx_product_name", columnList = "name" )
        }

)
@Builder
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", nullable = false, unique = true, updatable = false)
    private UUID publicId;

    @NotBlank(message = "Name is required")
    @Column(nullable = false, unique = true, length = 150)
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 20, max = 255)
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Price is required")
    @Min( value = 0, message = "Price cannot be negative")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Stock is required")
    @PositiveOrZero(message = "Stock cannot be negative")
    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

    @NotBlank(message = "Image url is required")
    @Column(name = "image_url", nullable = false)
    private String imageURl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate(){
        if(this.publicId == null){
            this.publicId = UUID.randomUUID();
        }
    }
}
