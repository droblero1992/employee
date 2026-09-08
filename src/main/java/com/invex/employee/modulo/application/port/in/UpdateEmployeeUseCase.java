package com.invex.employee.modulo.application.port.in;

import com.invex.employee.modulo.domain.model.Employee;

public interface UpdateEmployeeUseCase {
    void updateEmployee(String id, Employee employee);
}
