package com.example.prision.modules.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(
        {"cep",
         "logradouro",
         "complemento",
         "unidade",
         "bairro",
         "uf",
         "estado",
         "regiao",
         "ibge"})
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
