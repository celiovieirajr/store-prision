package com.example.prision.modules.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProductResponseDto {

    private Long id;
    private String description;
    private BigDecimal amountCost;
    private BigDecimal amount;
    private LocalDateTime dateUpdated;
    private LocalDate dateCreated;
    private CategoryResponseDto category;
}
