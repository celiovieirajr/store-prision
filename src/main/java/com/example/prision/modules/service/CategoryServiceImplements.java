package com.example.prision.modules.service;

import com.example.prision.exceptions.RequiredField;
import com.example.prision.modules.dto.CategoryRequestDto;
import com.example.prision.modules.dto.CategoryResponseDto;
import com.example.prision.modules.mapper.CategoryMapper;
import com.example.prision.modules.model.Category;
import com.example.prision.modules.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public CategoryResponseDto findCategoryById(int id) {

        if (id <= 0) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID less than 1");}

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return categoryMapper.toResponse(category);
    }


    public List<CategoryResponseDto> findAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toResponse).toList();
    }

    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {

        if (id <= 0) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID less than 1");}

        Category category = categoryRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        category.setName(categoryRequestDto.getName());

        Category categorySave = categoryRepository.save(category);
        return categoryMapper.toResponse(categorySave);
    }

    public void deleteCategoryById(Long id) {

        if (id <= 0) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID less than 1");}

        Category category = categoryRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
        );
        categoryRepository.delete(category);
    }
}
