package com.example.prision.modules.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleRequestDto {

    @NotEmpty(message = "At least one item is required")
    @Valid
    private List<ItemSaleRequestDto> itemsSaleRequestList;

    @NotBlank(message = "Sender customer name is required")
    private String nameCustomerSender;

    @NotBlank(message = "Sender customer phone is required")
    private String customerSenderPhone;

    @NotBlank(message = "Receiver customer name is required")
    private String nameCustomerReceiver;

    @NotNull(message = "Penitentiary id is required")
    @Positive(message = "Penitentiary id must be greater than zero")
    private Long idPenitentiary;

    private BigDecimal totalAmount;
}
