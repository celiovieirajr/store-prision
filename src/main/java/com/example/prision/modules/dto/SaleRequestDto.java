package com.example.prision.modules.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleRequestDto {

    private List<ItemSaleRequestDto> itemsSaleRequestList;

    private String nameCustomerSender;
    private String customerSenderPhone;

    private String nameCustomerReceiver;

    private Long idPenitentiary;

    private BigDecimal totalAmount;
}
