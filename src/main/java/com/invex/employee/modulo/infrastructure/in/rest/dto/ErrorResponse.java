package com.invex.employee.modulo.infrastructure.in.rest.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer code;
    private String message;
}
