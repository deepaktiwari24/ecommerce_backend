package com.deepak.ecommerce_backend.repository;

import com.deepak.ecommerce_backend.dto.CategoryDto;
import com.deepak.ecommerce_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("all")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    Optional<Category> findByPublicId(UUID publicId);


}
