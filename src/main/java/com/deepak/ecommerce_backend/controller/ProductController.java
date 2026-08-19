package com.deepak.ecommerce_backend.controller;

import com.deepak.ecommerce_backend.dto.ProductDto;
import com.deepak.ecommerce_backend.service.Impl.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("all")
@RestController
@RequestMapping("api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/admin/product")
    public ResponseEntity<ProductDto.ProductResponse> createProduct(@RequestBody ProductDto.ProductRequest productRequest){
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping("/user/products")
    public ResponseEntity<List<ProductDto.ProductResponse>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.FOUND);
    }

    @GetMapping("/products/category/{publicId}")
    public ResponseEntity<List<ProductDto.ProductResponse>> getProductByCategoryPublicId(@PathVariable("publicId") UUID publicId){
        return new ResponseEntity<>((productService.getProductByCategoryPublicId(publicId)), HttpStatus.FOUND);
    }
}
