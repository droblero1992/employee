package com.invex.employee.modulo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Employee {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private Integer age;
    private String gender;
    private LocalDate birthDate;
    private String position;
    private LocalDate startDate;
    private Boolean status;

    public Employee merge(final Employee employeeIn) {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setFirstName(employeeIn.getFirstName() == null ? this.getFirstName() : employeeIn.getFirstName());
        employee.setMiddleName(employeeIn.getMiddleName() == null ? this.getMiddleName() : employeeIn.getMiddleName());
        employee.setLastName(employeeIn.getLastName() == null ? this.getLastName() : employeeIn.getLastName());
        employee.setSecondLastName(employeeIn.getSecondLastName() == null ? this.getSecondLastName() : employeeIn.getSecondLastName());
        employee.setAge(employeeIn.getAge() == null ? this.getAge() : employeeIn.getAge());
        employee.setGender(employeeIn.getGender() == null ? this.getGender() : employeeIn.getGender());
        employee.setBirthDate(employeeIn.getBirthDate() == null ? this.getBirthDate() : employeeIn.getBirthDate());
        employee.setPosition(employeeIn.getPosition() == null ? this.getPosition() : employeeIn.getPosition());
        employee.setStartDate(getStartDate());
        employee.setStatus(employeeIn.getStatus() == null ? this.getStatus() : employeeIn.getStatus());
        return employee;
    }


}
