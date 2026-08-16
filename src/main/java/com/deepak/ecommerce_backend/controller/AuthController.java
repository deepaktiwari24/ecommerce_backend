package com.deepak.ecommerce_backend.controller;

import com.deepak.ecommerce_backend.dto.UserDto;
import com.deepak.ecommerce_backend.service.Impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@SuppressWarnings("all")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto.Response> register(@RequestBody UserDto.RegisterRequest userDto){

        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }
}
