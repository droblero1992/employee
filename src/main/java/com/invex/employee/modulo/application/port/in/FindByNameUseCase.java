package com.invex.employee.modulo.application.port.in;

import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.domain.model.PageQuery;
import com.invex.employee.modulo.domain.model.PageResult;

public interface FindByNameUseCase {
    PageResult<Employee> getEmployeesByName(PageQuery pageQuery, String name);
}
