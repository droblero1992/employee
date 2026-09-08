package com.invex.employee.modulo.infrastructure.out.persistence.mapper;

import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.infrastructure.out.persistence.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperEntity {
    public Employee entityToModel(final EmployeeEntity employeeEntity) {
        return new Employee(
                employeeEntity.getId(),
                employeeEntity.getFirstName(),
                employeeEntity.getMiddleName(),
                employeeEntity.getLastName(),
                employeeEntity.getSecondLastName(),
                employeeEntity.getAge(),
                employeeEntity.getGender(),
                employeeEntity.getBirthDate(),
                employeeEntity.getPosition(),
                employeeEntity.getStartDate(),
                employeeEntity.getStatus()
        );
    }
}
