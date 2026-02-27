package com.example.prision.modules.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
}
