package com.example.prision.modules.service;

import com.example.prision.exceptions.RequiredField;
import com.example.prision.modules.dto.ProductRequestDto;
import com.example.prision.modules.dto.ProductResponseDto;
import com.example.prision.modules.mapper.ProductMapper;
import com.example.prision.modules.model.Product;
import com.example.prision.modules.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplements implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImplements(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponseDto insertProduct(ProductRequestDto productRequestDto) {

        if (productRequestDto.getCategoryId() == null || productRequestDto.getCategoryId() == 0) {
            throw new RequiredField("Category Id is required");
        }

        if (productRequestDto.getDescription() == null || productRequestDto.getDescription().isEmpty() || productRequestDto.getDescription().isBlank()) {
            throw new RequiredField("Description is required");
        }

        Product model = productMapper.toModel(productRequestDto);
        Product saved = productRepository.save(model);

        return productMapper.toResponseDto(saved);

    }


}
