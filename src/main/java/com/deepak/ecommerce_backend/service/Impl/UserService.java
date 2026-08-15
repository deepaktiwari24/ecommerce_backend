package com.deepak.ecommerce_backend.service.Impl;

import com.deepak.ecommerce_backend.dto.UserDto;
import com.deepak.ecommerce_backend.entity.Cart;
import com.deepak.ecommerce_backend.entity.User;
import com.deepak.ecommerce_backend.repository.CartRepository;
import com.deepak.ecommerce_backend.repository.UserRepository;
import com.deepak.ecommerce_backend.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(CartRepository cartRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDto.Response registerUser(UserDto.RegisterRequest userDto) {
        if (userRepository.existsByEmail(userDto.email())) {
            throw new RuntimeException("Email is already registered!");
        }
        User user = new User();
        user.setName(userDto.name());
        user.setEmail(userDto.email());
        user.setPhone(userDto.phone());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        User savedUser = userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setTotalPrice(BigDecimal.valueOf(0));
        cartRepository.save(cart);
        return new UserDto.Response(
                savedUser.getPublicId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getIsActive(),
                savedUser.getLastLoginAt(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt(),
                savedUser.getRole()
                );
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
