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
}
