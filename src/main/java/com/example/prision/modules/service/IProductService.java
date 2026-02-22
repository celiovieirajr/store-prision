package com.example.prision.modules.service;

import com.example.prision.modules.dto.ProductRequestDto;
import com.example.prision.modules.dto.ProductResponseDto;

public interface IProductService {
    ProductResponseDto insertProduct(ProductRequestDto productRequestDto);
}
