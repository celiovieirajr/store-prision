package com.example.prision.modules.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleResponseDto {

    private Long id;

    private List<ItemSaleResponseDto> itemSaleResponseDtoList;

    private String nameCustomerSender;
    private String customerSenderPhone;

    private String nameCustomerReceiver;

    private PenitentiaryResponseDto penitentiaryResponseDto;

    private BigDecimal totalAmount;
}
