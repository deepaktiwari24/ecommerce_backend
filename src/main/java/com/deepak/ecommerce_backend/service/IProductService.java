package com.deepak.ecommerce_backend.service;

import com.deepak.ecommerce_backend.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface IProductService {

   ProductDto.ProductResponse createProduct(ProductDto.ProductRequest productRequest);

   List<ProductDto.ProductResponse> getAllProduct();

   List<ProductDto.ProductResponse> getProductByCategoryPublicId (UUID publicId);


}
