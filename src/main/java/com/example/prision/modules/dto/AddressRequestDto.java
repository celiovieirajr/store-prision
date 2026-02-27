package com.example.prision.modules.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressRequestDto {

    @NotBlank(message = "CEP is required")
    private String cep;

    @NotBlank(message = "Street is required")
    private String logradouro;

    private String complemento;
    private String unidade;

    @NotBlank(message = "Neighborhood is required")
    private String bairro;

    @NotBlank(message = "UF is required")
    private String uf;

    @NotBlank(message = "State is required")
    private String estado;

    private String regiao;
    private String ibge;
}
