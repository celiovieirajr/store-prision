package com.example.prision.modules.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder(
        {"id",
        "saleResponseDto",
        "productResponseDto",
        "quantity",
        "amount",
        "totalAmount"})
public class ItemSaleResponseDto {

    private Long id;
    private SaleResponseDto saleResponseDto;
    private ProductResponseDto productResponseDto;
    private Integer quantity;
    private BigDecimal amount;
    private BigDecimal totalAmount;
}
