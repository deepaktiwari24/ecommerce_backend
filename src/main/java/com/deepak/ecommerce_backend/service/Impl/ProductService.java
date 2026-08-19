package com.deepak.ecommerce_backend.service.Impl;

import com.deepak.ecommerce_backend.dto.ProductDto;
import com.deepak.ecommerce_backend.entity.Category;
import com.deepak.ecommerce_backend.entity.Product;
import com.deepak.ecommerce_backend.repository.CategoryRepository;
import com.deepak.ecommerce_backend.repository.ProductRepository;
import com.deepak.ecommerce_backend.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDto.ProductResponse createProduct(ProductDto.ProductRequest productRequest) {
        Category category = categoryRepository.findByPublicId(productRequest.publicId())
                .orElseThrow(() -> new RuntimeException("No Orphan allowed"));

        Product product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        product.setImageURl(productRequest.imageURL());
        product.setStockQuantity(productRequest.stockQuantity());
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);

        return new ProductDto.ProductResponse(
                savedProduct.getPublicId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getStockQuantity(),
                savedProduct.getImageURl(),
                savedProduct.getCreatedAt(),
                savedProduct.getUpdatedAt(),
                savedProduct.getCategory().getName()
        );
    }

    @Override
    public List<ProductDto.ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream().map(product -> new ProductDto.ProductResponse(
                        product.getPublicId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStockQuantity(),
                        product.getImageURl(),
                        product.getCreatedAt(),
                        product.getUpdatedAt(),
                        product.getCategory().getName()
                )).toList();
    }

    @Override
    public List<ProductDto.ProductResponse> getProductByCategoryPublicId(UUID publicId) {
       return productRepository.findAllByCategoryPublicId(publicId)
                .stream().map(product -> new ProductDto.ProductResponse(
                        product.getPublicId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStockQuantity(),
                        product.getImageURl(),
                        product.getCreatedAt(),
                        product.getUpdatedAt(),
                        product.getCategory().getName()
                )).toList();
    }
}
