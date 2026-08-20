package com.deepak.ecommerce_backend.service;

import com.deepak.ecommerce_backend.dto.UserDto;
import com.deepak.ecommerce_backend.entity.User;

import java.util.Optional;

public interface IUserService {

    UserDto.Response registerUser(UserDto.RegisterRequest user);

    UserDto.Response getUserByEmail(String email);
}
