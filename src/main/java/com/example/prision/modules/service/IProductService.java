package com.example.prision.modules.service;

import com.example.prision.modules.dto.ProductRequestDto;
import com.example.prision.modules.dto.ProductResponseDto;

import java.util.List;

public interface IProductService {
    ProductResponseDto insertProduct(ProductRequestDto productRequestDto);
    ProductResponseDto findProductById(Long id);
    List<ProductResponseDto> findAllProducts();
    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);
    void deleteProduct(Long id);
}
