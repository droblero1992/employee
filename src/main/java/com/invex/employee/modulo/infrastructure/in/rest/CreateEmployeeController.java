package com.invex.employee.modulo.infrastructure.in.rest;

import com.invex.employee.modulo.application.port.in.AddEmployeeUseCase;
import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.infrastructure.in.rest.dto.CreateEmployeeReq;
import com.invex.employee.modulo.infrastructure.in.rest.dto.EmployeeDTO;
import com.invex.employee.modulo.infrastructure.in.rest.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class CreateEmployeeController {
    private final AddEmployeeUseCase addEmployeeUseCase;
    private final EmployeeMapper employeeMapper;
    @PostMapping
    public ResponseEntity<Void> create(@NotNull @Valid @RequestBody CreateEmployeeReq employeeDTO) {
        addEmployeeUseCase.addEmployee(employeeDTO.getEmployees().stream().map(employeeMapper::employeeDTOToModel).toList());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@NotNull @Valid @RequestBody CreateEmployeeReq employeeDTO, @PathVariable String id) {
        addEmployeeUseCase.addEmployee(employeeDTO.getEmployees().stream().map(employeeMapper::employeeDTOToModel).toList());
        return ResponseEntity.noContent().build();
    }
}
