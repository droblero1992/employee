package com.invex.employee.modulo.infrastructure.out.persistence;

import com.invex.employee.modulo.domain.model.Employee;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    private String secondLastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private Boolean status;

    public EmployeeEntity() {}

    public static EmployeeEntity fromDomain(final Employee emp) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.id = emp.getId();
        entity.firstName = emp.getFirstName();
        entity.middleName = emp.getMiddleName();
        entity.lastName = emp.getLastName();
        entity.secondLastName = emp.getSecondLastName();
        entity.age = emp.getAge();
        entity.gender = emp.getGender();
        entity.birthDate = emp.getBirthDate();
        entity.setPosition(emp.getPosition());
        entity.setStartDate(LocalDate.now());
        entity.setStatus(emp.getStatus());
        return entity;
    }

}
