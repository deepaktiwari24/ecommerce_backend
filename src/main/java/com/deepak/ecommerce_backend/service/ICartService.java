package com.deepak.ecommerce_backend.service;

import com.deepak.ecommerce_backend.dto.CartDto;
import com.deepak.ecommerce_backend.dto.CartItemDto;

import java.util.UUID;

public interface ICartService {
    CartDto.CartResponse addItemToCart(UUID userPublicId, CartItemDto.CartItemRequest cartItemRequest);
    CartDto.CartResponse removeItemFromCart(UUID userPublicId,UUID productPublicId);
    void clearCart(UUID userPublicId);
}
