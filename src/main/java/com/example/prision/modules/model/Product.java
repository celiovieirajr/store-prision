package com.example.prision.modules.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "product")
@Data
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amountCost;
    private BigDecimal amount;
    private LocalDateTime dateTimeUpdated;
    private LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
