package com.example.prision.modules.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(
        {"id",
        "name",
        "addressResponse"})
public class PenitentiaryResponseDto {

    private Long id;
    private String name;
    private AddressResponse addressResponse;
}
