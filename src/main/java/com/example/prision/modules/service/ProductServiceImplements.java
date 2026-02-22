package com.example.prision.modules.service;

import com.example.prision.exceptions.RequiredField;
import com.example.prision.modules.dto.ProductRequestDto;
import com.example.prision.modules.dto.ProductResponseDto;
import com.example.prision.modules.mapper.ProductMapper;
import com.example.prision.modules.model.Category;
import com.example.prision.modules.model.Product;
import com.example.prision.modules.repository.CategoryRepository;
import com.example.prision.modules.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImplements implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductServiceImplements(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponseDto insertProduct(ProductRequestDto productRequestDto) {

        validateProduct(productRequestDto); // validation basic modification product
        Product model = productMapper.toModel(productRequestDto);
        Product saved = productRepository.save(model);

        return productMapper.toResponseDto(saved);
    }

    public ProductResponseDto findProductById(Long id) {

        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product id");
        }

        Product model = productRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return productMapper.toResponseDto(model);
    }

    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toResponseDto).toList();
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {

        validateProduct(productRequestDto); // validation basic modification product

        Product model = productRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        model.setDateTimeUpdated(LocalDateTime.now());
        model.setAmount(productRequestDto.getAmount());
        model.setDescription(productRequestDto.getDescription());
        model.setAmountCost(productRequestDto.getAmountCost());
        model.setDateCreated(productRequestDto.getDateCreated());

        Category category = categoryRepository.findById(Math.toIntExact(productRequestDto.getCategoryId())).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));


        model.setCategory(category);

        Product saved = productRepository.save(model);
        return productMapper.toResponseDto(saved);
    }

    public void deleteProduct(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product id");
        }

        Product model = productRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        productRepository.delete(model);
    }

    private void validateProduct(ProductRequestDto productRequestDto) {
        if (productRequestDto.getCategoryId() == null || productRequestDto.getCategoryId() == 0) {
            throw new RequiredField("Category Id is required");
        }

        if (productRequestDto.getDescription() == null || productRequestDto.getDescription().isEmpty() || productRequestDto.getDescription().isBlank()) {
            throw new RequiredField("Description is required");
        }
    }


}
