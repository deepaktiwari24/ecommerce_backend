package com.deepak.ecommerce_backend.repository;

import com.deepak.ecommerce_backend.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("all")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   boolean existsByEmail(String email);

   Optional<User> findByEmail(String email);

}
