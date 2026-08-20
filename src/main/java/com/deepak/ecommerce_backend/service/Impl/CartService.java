package com.deepak.ecommerce_backend.service.Impl;

import com.deepak.ecommerce_backend.dto.CartDto;
import com.deepak.ecommerce_backend.dto.CartItemDto;
import com.deepak.ecommerce_backend.entity.Cart;
import com.deepak.ecommerce_backend.entity.CartItem;
import com.deepak.ecommerce_backend.entity.Product;
import com.deepak.ecommerce_backend.repository.CartRepository;
import com.deepak.ecommerce_backend.repository.ProductRepository;
import com.deepak.ecommerce_backend.service.ICartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public CartDto.CartResponse addItemToCart(UUID userPublicId, CartItemDto.CartItemRequest cartItemRequest) {
        Cart cart = cartRepository.findByPublicId(userPublicId.toString())
                .orElseThrow(() -> new RuntimeException("No User found"));
        Product product = productRepository.findByPublicId(cartItemRequest.productPublicId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Optional<CartItem> existingCartItem = cart.getCartItem()
                .stream().filter(cartItem -> cartItem.getProduct().equals(product)).findFirst();
        if (existingCartItem.isPresent()) {
            int updatedQuantity = existingCartItem.get().getQuantity() + cartItemRequest.quantity();
            existingCartItem.get().setQuantity(updatedQuantity);
        } else {
            cart.getCartItem().add(CartItem.builder()
                    .cart(cart)
                    .quantity(cartItemRequest.quantity())
                    .product(product)
                    .build());
        }
        BigDecimal aggregatedTotal = calculatePrice(cart);
        cart.setTotalPrice(aggregatedTotal);

        Cart savedCart = cartRepository.save(cart);
        return new CartDto.CartResponse(
                savedCart.getPublicId(),
                savedCart.getTotalPrice()
        );
    }

    @Override
    @Transactional
    public CartDto.CartResponse removeItemFromCart(UUID userPublicId, UUID productPublicId) {
        Cart cart = cartRepository.findByPublicId(userPublicId.toString())
                .orElseThrow(() -> new RuntimeException("No active cart"));
        CartItem cartItemToRemove = cart.getCartItem().stream()
                .filter(cartItem -> cartItem.getProduct().getPublicId().equals(productPublicId)).findFirst().orElseThrow(() -> new RuntimeException("no active product in cartItems"));
        cart.getCartItem().remove(cartItemToRemove);
        BigDecimal recalculatedPrice = calculatePrice(cart);
        cart.setTotalPrice(recalculatedPrice);
        Cart savedCart = cartRepository.save(cart);
        return new CartDto.CartResponse(
                savedCart.getPublicId(),
                savedCart.getTotalPrice()
        );
    }

    @Override
    @Transactional
    public void clearCart(UUID userPublicId) {
        Cart cart = cartRepository.findByPublicId(userPublicId.toString()).orElseThrow(() -> new RuntimeException("No Active cart"));
        cart.getCartItem().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    private static BigDecimal calculatePrice(Cart cart) {
        return cart.getCartItem().stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
