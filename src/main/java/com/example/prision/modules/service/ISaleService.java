package com.example.prision.modules.service;

import com.example.prision.modules.dto.SaleRequestDto;
import com.example.prision.modules.dto.SaleResponseDto;

import java.util.List;

public interface ISaleService {
    SaleResponseDto createSale(SaleRequestDto saleRequestDto);
    SaleResponseDto findById(Long idSale);
    List<SaleResponseDto> findAll();
    SaleResponseDto updatedSale(Long idSale, SaleRequestDto requestDto);
    void deleteById(Long idSale);
}
