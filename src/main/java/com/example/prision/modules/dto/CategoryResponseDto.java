package com.example.prision.modules.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(
        {"id",
         "name"})
public class CategoryResponseDto {

    private Long id;
    private String name;
}
