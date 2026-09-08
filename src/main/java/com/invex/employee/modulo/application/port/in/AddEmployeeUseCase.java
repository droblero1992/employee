package com.invex.employee.modulo.application.port.in;

import com.invex.employee.modulo.application.port.in.dto.EmployeeDTO;
import com.invex.employee.modulo.domain.model.Employee;

import java.util.List;

public interface AddEmployeeUseCase {
    void addEmployee(List<Employee> employee);
}
