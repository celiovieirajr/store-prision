package com.example.prision.modules.service;

import com.example.prision.modules.dto.CategoryRequestDto;
import com.example.prision.modules.dto.CategoryResponseDto;

public interface ICategoryService {
    public CategoryResponseDto insertCategory(CategoryRequestDto categoryRequestDto);
}
