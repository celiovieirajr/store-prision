package com.example.prision.exceptions;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class ApiErrorDto {

    private final OffsetDateTime timestamp = OffsetDateTime.now();
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final List<FieldErrorDto> fieldErrors;

    public ApiErrorDto(int status, String error, String message, String path, List<FieldErrorDto> fieldErrors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }
}
