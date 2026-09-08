package com.invex.employee.modulo.infrastructure.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeePageResult<T> {
    private List<T> items;
    private Integer page;
}
