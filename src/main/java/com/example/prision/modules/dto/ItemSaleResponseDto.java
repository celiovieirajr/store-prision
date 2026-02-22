package com.example.prision.modules.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemSaleResponseDto {

    private Long id;
    private SaleResponseDto saleResponseDto;
    private ProductResponseDto productResponseDto;
    private Integer quantity;
    private BigDecimal amount;
    private BigDecimal totalAmount;
}
