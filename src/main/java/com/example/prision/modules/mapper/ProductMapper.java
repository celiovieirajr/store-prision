package com.example.prision.modules.mapper;

import com.example.prision.modules.dto.CategoryResponseDto;
import com.example.prision.modules.dto.ProductRequestDto;
import com.example.prision.modules.dto.ProductResponseDto;
import com.example.prision.modules.model.Category;
import com.example.prision.modules.model.Product;
import com.example.prision.modules.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public ProductMapper(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Product toModel(ProductRequestDto productRequestDto) {
        Product product = new Product();

        Category category = categoryRepository.findById(productRequestDto.getCategoryId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found: "  + productRequestDto.getCategoryId()));

        product.setCategory(category);
        product.setDescription(productRequestDto.getDescription());
        product.setAmount(productRequestDto.getAmount());
        product.setAmountCost(productRequestDto.getAmountCost());
        product.setDateCreated(productRequestDto.getDateCreated());
        product.setDateTimeUpdated(LocalDateTime.now());

        return product;
    }

    public ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto response = new ProductResponseDto();

        CategoryResponseDto categoryResponseDto = categoryMapper.toResponse(product.getCategory());


        response.setId(product.getId());
        response.setCategory(categoryResponseDto);
        response.setDescription(product.getDescription());
        response.setAmount(product.getAmount());
        response.setAmountCost(product.getAmountCost());
        response.setDateCreated(product.getDateCreated());
        response.setDateUpdated(product.getDateTimeUpdated());

        return response;
    }
}
