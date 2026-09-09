package com.invex.employee.modulo.application.service;


import com.invex.employee.modulo.application.exception.EmployeeNotFoundException;
import com.invex.employee.modulo.application.port.out.EmployeeRepository;
import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.domain.model.PageQuery;
import com.invex.employee.modulo.domain.model.PageResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void addEmployeeTestOk() {
        List<Employee> employees = new ArrayList<>();
        employeeService.addEmployee(employees);
        Mockito.verify(employeeRepository).saveAll(Mockito.any());
    }

    @Test
    void getAllEmployeesTestOk() {
        PageQuery pageQuery = new PageQuery(0, 10);
        PageResult<Employee> resultMock = new PageResult<>();
        Mockito.when(employeeRepository.findAll(Mockito.any())).thenReturn(resultMock);
        PageResult<Employee> result = employeeService.getAllEmployees(pageQuery);
        Assertions.assertNotNull(result);
    }

    @Test
    void getAllEmployeeByIdTestOk() {
        Mockito.when(employeeRepository.findById("uuid")).thenReturn(Optional.of(new Employee()));
        Employee result = employeeService.getEmployeeById("uuid");
        Assertions.assertNotNull(result);
    }

    @Test
    void getAllEmployeeByIdNotFoundTestOk() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById("uuid"));
    }

    @Test
    void updateEmployeeTestOk() {
        Employee updateEmployee = new Employee();
        Employee findEmployee = new Employee();
        updateEmployee.setStatus(false);
        Mockito.when(employeeRepository.findById("uuid")).thenReturn(Optional.of(findEmployee));
        employeeService.updateEmployee("uuid", updateEmployee);
        Mockito.verify(employeeRepository).saveAll(Mockito.any());
    }

    @Test
    void deleteEmployeeTestOk() {
        Employee findEmployee = new Employee();
        Mockito.when(employeeRepository.findById("uuid")).thenReturn(Optional.of(findEmployee));
        employeeService.deleteEmployee("uuid");
        Mockito.verify(employeeRepository).deleteById(Mockito.any());
    }

    @Test
    void deleteEmployeeByIdNotFoundTestOk() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee("uuid"));
    }

    @Test
    void getEmployeeByNameTestOk() {
        PageQuery pageQuery = new PageQuery(0, 10);
        Mockito.when(employeeRepository.findEmployeesByName(pageQuery, "name")).thenReturn(new PageResult<>());
        PageResult<Employee> response = employeeService.getEmployeesByName(pageQuery, "name");
        Assertions.assertNotNull(response);
    }
}
