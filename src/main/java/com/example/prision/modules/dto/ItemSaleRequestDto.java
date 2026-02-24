package com.example.prision.modules.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemSaleRequestDto {

    private Long id;
    private Long IdSaleRequestDto;
    private Long IdProductRequestDto;
    private Integer quantity;
    private BigDecimal totalAmount;
}
