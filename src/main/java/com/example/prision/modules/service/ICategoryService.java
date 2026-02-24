package com.example.prision.modules.service;

import com.example.prision.modules.dto.CategoryRequestDto;
import com.example.prision.modules.dto.CategoryResponseDto;

import java.util.List;

public interface ICategoryService {
    public CategoryResponseDto insertCategory(CategoryRequestDto categoryRequestDto);
    public CategoryResponseDto findCategoryById(Long id);
    public List<CategoryResponseDto> findAllCategories();
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto);
    public void deleteCategoryById(Long id);
}
