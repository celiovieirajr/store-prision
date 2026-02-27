package com.example.prision.modules.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PenitentiaryRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Address is required")
    @Valid
    private AddressRequestDto addressRequestDto;
}
