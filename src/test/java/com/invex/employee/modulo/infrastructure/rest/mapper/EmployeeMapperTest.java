package com.invex.employee.modulo.infrastructure.rest.mapper;

import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.infrastructure.in.rest.dto.EmployeeDTO;
import com.invex.employee.modulo.infrastructure.in.rest.mapper.EmployeeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeMapperTest {
    EmployeeMapper employeeMapper = new EmployeeMapper();

    @Test
    void employeeModelToDTOTestOk(){
        EmployeeDTO employeeDTO = employeeMapper.employeeModelToDTO(new Employee());
        Assertions.assertNotNull(employeeDTO);
    }

    @Test
    void employeeDTOTOModelTestOk(){
        Employee employee = employeeMapper.employeeDTOToModel(new EmployeeDTO());
        Assertions.assertNotNull(employee);
    }
}
