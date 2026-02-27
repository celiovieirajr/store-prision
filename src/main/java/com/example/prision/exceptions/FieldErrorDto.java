package com.example.prision.exceptions;

import lombok.Data;

@Data
public class FieldErrorDto {

    private final String field;
    private final String message;

    public FieldErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
