package com.deepak.ecommerce_backend.controller;

import com.deepak.ecommerce_backend.dto.CartDto;
import com.deepak.ecommerce_backend.dto.CartItemDto;
import com.deepak.ecommerce_backend.service.Impl.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SuppressWarnings("all")
@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/items")
    public ResponseEntity<CartDto.CartResponse> addItemToCart(@RequestParam(name = "userPublicId") UUID userPublicId, @RequestBody CartItemDto.CartItemRequest cartItemRequest){
        return new ResponseEntity<>(cartService.addItemToCart(userPublicId,cartItemRequest), HttpStatus.OK);
    }

    @DeleteMapping("/items/{productPublicId}")
    public ResponseEntity<CartDto.CartResponse> removeItemFromCart(@PathVariable("productPublicId") UUID productPublicId, @RequestParam("userPublicId") UUID userPublicId){
        return new ResponseEntity<>(cartService.removeItemFromCart(userPublicId,productPublicId),HttpStatus.OK);
    }

    @DeleteMapping
    public void clearCart(@RequestParam("userPublicId") UUID userPublicId){
        cartService.clearCart(userPublicId);
    }
}
