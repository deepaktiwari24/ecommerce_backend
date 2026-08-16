package com.deepak.ecommerce_backend.service;

import com.deepak.ecommerce_backend.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    CategoryDto.CategoryResponse createCategory(CategoryDto.CategoryRequest categoryRequest);

    List<CategoryDto.CategoryResponse> getAllCategories();

}
