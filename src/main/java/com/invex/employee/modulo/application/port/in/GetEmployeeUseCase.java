package com.invex.employee.modulo.application.port.in;

import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.domain.model.PageQuery;
import com.invex.employee.modulo.domain.model.PageResult;

import java.util.List;

public interface GetEmployeeUseCase {

    PageResult<Employee> getAllEmployees(PageQuery pageQuery);

    Employee getEmployeeById(String id);
}
