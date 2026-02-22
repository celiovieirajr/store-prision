package com.example.prision.modules.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductRequestDto {

    private String description;
    private BigDecimal amountCost;
    private BigDecimal amount;
    private LocalDate dateCreated;
    private Long categoryId;
}
