package com.example.prision.modules.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductRequestDto {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Cost amount is required")
    @Positive(message = "Cost amount must be greater than zero")
    private BigDecimal amountCost;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Creation date is required")
    private LocalDate dateCreated;

    @NotNull(message = "Category id is required")
    @Positive(message = "Category id must be greater than zero")
    private Long categoryId;
}
