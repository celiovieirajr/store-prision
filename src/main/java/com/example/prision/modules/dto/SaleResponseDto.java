package com.example.prision.modules.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonPropertyOrder(
        {"id",
        "nameCustomerSender",
        "customerSenderPhone",
        "nameCustomerReceiver",
        "penitentiaryResponseDto",
        "items",
        "totalAmount"})
public class SaleResponseDto {

    private Long id;

    private List<ItemSaleResponseDto> items;

    private String nameCustomerSender;
    private String customerSenderPhone;

    private String nameCustomerReceiver;

    private PenitentiaryResponseDto penitentiary;

    private BigDecimal totalAmount;
}
