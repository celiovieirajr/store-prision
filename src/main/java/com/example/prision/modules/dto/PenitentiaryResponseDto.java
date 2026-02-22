package com.example.prision.modules.dto;

import lombok.Data;

@Data
public class PenitentiaryResponseDto {

    private Long id;
    private String name;
    private AddressResponse addressResponse;
}
