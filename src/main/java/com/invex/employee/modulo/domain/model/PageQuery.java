package com.invex.employee.modulo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageQuery {
    private Integer page;
    private Integer size;
}
