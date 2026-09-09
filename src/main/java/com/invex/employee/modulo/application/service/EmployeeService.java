package com.invex.employee.modulo.application.service;

import com.invex.employee.modulo.application.exception.EmployeeNotFoundException;
import com.invex.employee.modulo.application.port.in.*;
import com.invex.employee.modulo.application.port.in.dto.EmployeeDTO;
import com.invex.employee.modulo.application.port.out.EmployeeRepository;
import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.domain.model.PageQuery;
import com.invex.employee.modulo.domain.model.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements AddEmployeeUseCase, GetEmployeeUseCase, UpdateEmployeeUseCase, DeleteEmployeeUseCase, FindByNameUseCase {
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
    private final EmployeeRepository employeeRepository;

    @Override
    public void addEmployee(final List<Employee> employee) {
        employeeRepository.saveAll(employee);
    }

    @Override
    public PageResult<Employee> getAllEmployees(final PageQuery pageQuery) {
        return employeeRepository.findAll(pageQuery);
    }

    @Override
    public Employee getEmployeeById(final String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
    }

    @Override
    public void updateEmployee(final String id, final Employee employee) {
        employee.setId(id);
        Employee employeeOne = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        Employee update = employeeOne.merge(employee);
        employeeRepository.saveAll(List.of(update));
    }

    @Override
    public void deleteEmployee(final String id) {
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        employeeRepository.deleteById(id);
    }

    @Override
    public PageResult<Employee> getEmployeesByName(final PageQuery pageQuery, final String name) {
        return employeeRepository.findEmployeesByName(pageQuery, name);
    }
}