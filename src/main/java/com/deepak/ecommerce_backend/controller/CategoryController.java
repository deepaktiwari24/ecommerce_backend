package com.deepak.ecommerce_backend.controller;

import com.deepak.ecommerce_backend.dto.CategoryDto;
import com.deepak.ecommerce_backend.service.Impl.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("all")
@RestController
@RequestMapping("api/v1/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto.CategoryResponse> createCategory(@RequestBody CategoryDto.CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.createCategory(categoryRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto.CategoryResponse>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.FOUND);
    }

}
