package com.deepak.ecommerce_backend.dto;

import com.deepak.ecommerce_backend.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserDto {

    public record RegisterRequest(

            @NotBlank(message = "name is required")
            @Column(nullable = false, unique = true, length = 50)
            String name,

            @NotBlank(message = "password is required")
            @Column(nullable = false, length = 60)
            String password,

            @NotBlank(message = "phone number is required")
            @Column(nullable = false, unique = true, length = 15)
            String phone,

            @NotBlank(message = "Email is required")
            @Email(message = "Enter valid email")
            @Column(nullable = false, unique = true, length = 160)
            String email
    ){}

    public record Response(
            UUID publicId,
            String name,
            String email,
            String phone,
            boolean isActive,
            LocalDateTime lastLoginAt,
            Instant createdAt,
            Instant updateAt,
            Role role
    ){}

}
