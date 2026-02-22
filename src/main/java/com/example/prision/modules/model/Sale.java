package com.example.prision.modules.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity @Table(name = "sale")
@Data
public class Sale {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemSale>  itemSalesList;

    private String nameCustomerSender;
    private String customerSenderPhone;

    private String nameCustomerReceiver;

    @OneToOne
    private Penitentiary penitentiary;

    private BigDecimal totalAmount;
}
