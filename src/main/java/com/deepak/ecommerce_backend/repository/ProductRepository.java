package com.deepak.ecommerce_backend.repository;

import com.deepak.ecommerce_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("all")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByPublicId(UUID PublicId);

    List<Product> findAllByCategoryPublicId(UUID publicId);

}
