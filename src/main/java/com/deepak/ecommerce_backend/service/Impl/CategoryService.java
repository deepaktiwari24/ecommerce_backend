package com.deepak.ecommerce_backend.service.Impl;

import com.deepak.ecommerce_backend.dto.CategoryDto;
import com.deepak.ecommerce_backend.entity.Category;
import com.deepak.ecommerce_backend.repository.CategoryRepository;
import com.deepak.ecommerce_backend.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto.CategoryResponse createCategory(CategoryDto.CategoryRequest categoryRequest) {
        if(categoryRepository.existsByName(categoryRequest.name())){
            throw new IllegalArgumentException("Category already exists");
        }
        Category category = new Category();
        category.setName(categoryRequest.name());
        Category savedCategory = categoryRepository.save(category);
        return new CategoryDto.CategoryResponse(
                savedCategory.getPublicId(),
                savedCategory.getName(),
                savedCategory.getCreatedAt(),
                savedCategory.getUpdatedAt());
    }

    @Override
    public List<CategoryDto.CategoryResponse> getAllCategories() {
        List<Category> category = categoryRepository.findAll();
        return  category.stream()
                .map(e -> new CategoryDto.CategoryResponse(e.getPublicId(),e.getName(),e.getCreatedAt(),e.getUpdatedAt()))
                .collect(Collectors.toList());
    }
}
