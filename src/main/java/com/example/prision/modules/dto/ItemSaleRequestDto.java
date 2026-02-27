package com.example.prision.modules.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemSaleRequestDto {

    @NotNull(message = "Product id is required")
    @Positive(message = "Product id must be greater than zero")
    private Long idProduct;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;
}
