package com.example.prision.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Table(name = "product")
@Data
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal amountCost;
    private BigDecimal amount;
    private LocalDate dateUpdated;
    private LocalDate dateCreated;
    private Category category;
}
