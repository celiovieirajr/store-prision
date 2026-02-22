package com.example.prision.modules.service;

import com.example.prision.exceptions.RequiredField;
import com.example.prision.modules.dto.CategoryRequestDto;
import com.example.prision.modules.dto.CategoryResponseDto;
import com.example.prision.modules.mapper.CategoryMapper;
import com.example.prision.modules.model.Category;
import com.example.prision.modules.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CategoryServiceImplements implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImplements(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponseDto insertCategory(CategoryRequestDto categoryRequestDto) {

        if (categoryRequestDto.getName().isEmpty() || categoryRequestDto.getName().length() < 2) {
            throw new RequiredField("Category name is mandatory");
        }

        Category category = categoryMapper.toModel(categoryRequestDto);
        Category categorySaved = categoryRepository.save(category);

        return categoryMapper.toResponse(categorySaved);
    }
}
