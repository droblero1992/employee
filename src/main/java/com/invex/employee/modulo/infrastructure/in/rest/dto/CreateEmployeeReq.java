package com.invex.employee.modulo.infrastructure.in.rest.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateEmployeeReq {

    @Valid
    @NotNull
    @Size(min = 1, max = 10)
    private List<EmployeeDTO> employees;
}
