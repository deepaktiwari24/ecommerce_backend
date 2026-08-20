package com.deepak.ecommerce_backend.dto;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CartDto {
    public record CartResponse(
            UUID publicId,
            BigDecimal totalPrice
    ){}
}
