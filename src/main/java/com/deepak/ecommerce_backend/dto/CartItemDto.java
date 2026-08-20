package com.deepak.ecommerce_backend.dto;

import com.deepak.ecommerce_backend.entity.Cart;
import com.deepak.ecommerce_backend.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class CartItemDto {
    public record CartItemRequest(

            @NotNull( message = "Product Public Id is required")
            UUID productPublicId,

            @Min(value = 1, message = "Quantity must be at least 1")
            Integer quantity,

            @NotNull(message = "Cart Public Id is required")
            UUID cartPublicId
    ){}
}
