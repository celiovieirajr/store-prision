package com.example.prision.modules.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder(
        {"id",
        "description",
        "amountCost",
        "amount",
        "category",
        "dateCreated",
        "dateUpdated"})
public class ProductResponseDto {

    private Long id;
    private String description;
    private BigDecimal amountCost;
    private BigDecimal amount;
    private LocalDateTime dateUpdated;
    private LocalDate dateCreated;
    private CategoryResponseDto category;
}
