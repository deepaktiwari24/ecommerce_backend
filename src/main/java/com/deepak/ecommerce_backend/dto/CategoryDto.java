package com.deepak.ecommerce_backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.UUID;

public class CategoryDto {

    public record CategoryRequest(
            @NotBlank(message = "Category name is required")
            @Column(nullable = false, length = 160, unique = true)
            String name
    ){};

    public record CategoryResponse(
            UUID publicId,
            String name,
            Instant createdAt,
            Instant updateAt
    ){};

}
