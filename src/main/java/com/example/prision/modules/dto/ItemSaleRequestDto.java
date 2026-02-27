package com.example.prision.modules.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemSaleRequestDto {

    private Long idProduct;
    private Integer quantity;
}
