package com.invex.employee.modulo.infrastructure.in.rest.mapper;

import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.infrastructure.in.rest.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmployeeMapper {
    public EmployeeDTO employeeModelToDTO(final Employee employee){
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .age(employee.getAge())
                .middleName(employee.getMiddleName())
                .gender(employee.getGender())
                .secondLastName(employee.getSecondLastName())
                .birthDate(employee.getBirthDate())
                .status(employee.getStatus())
                .position(employee.getPosition())
                .startDate(employee.getStartDate())
                .build();
    }


    public Employee employeeDTOToModel(final EmployeeDTO employee){
        return Employee.builder()
                .id(UUID.randomUUID().toString())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .age(employee.getAge())
                .middleName(employee.getMiddleName())
                .gender(employee.getGender())
                .secondLastName(employee.getSecondLastName())
                .birthDate(employee.getBirthDate())
                .position(employee.getPosition())
                .status(employee.getStatus())
                .startDate(employee.getStartDate())
                .build();
    }
}
