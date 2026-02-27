package com.example.prision.modules.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder(
        {"id",
        "productResponseDto",
        "quantity",
        "amount",
        "totalAmount"})
public class ItemSaleResponseDto {

    private Long id;
    private ProductResponseDto product;
    private Integer quantity;
    private BigDecimal totalAmount;
}
