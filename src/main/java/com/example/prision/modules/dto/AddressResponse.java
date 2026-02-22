package com.example.prision.modules.dto;

import lombok.Data;

@Data
public class AddressResponse {

    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
}
