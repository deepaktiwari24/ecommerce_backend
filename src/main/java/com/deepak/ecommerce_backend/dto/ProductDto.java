package com.deepak.ecommerce_backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class ProductDto {
    public record ProductRequest(

            @NotBlank(message = "name is required")
            @Column(nullable = false, unique = true, length = 50)
            String name,

            @NotBlank(message = "Description is required")
            @Size(min = 20, max = 1000)
            @Column(nullable = false)
            String description,

            @NotNull(message = "Price is required")
            @Min(value = 0, message = "Price cannot be negative")
            @Column(nullable = false, precision = 12, scale = 2)
            BigDecimal price,

            @Min(value = 1, message = "Quantity must be at least 1")
            int stockQuantity,

            @NotBlank(message = "Image URL is required")
            String imageURL,

            @NotNull(message = "Public ID is mandatory")
            UUID publicId
    ) {
    }

    public record ProductResponse(
            UUID publicId,
            String name,
            String description,
            BigDecimal price,
            int stockQuantity,
            String imageURL,
            Instant createdAt,
            Instant updateAt,
            String categoryName
    ) {
    }

}
