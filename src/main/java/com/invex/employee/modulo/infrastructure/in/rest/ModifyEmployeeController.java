package com.invex.employee.modulo.infrastructure.in.rest;

import com.invex.employee.modulo.application.port.in.AddEmployeeUseCase;
import com.invex.employee.modulo.application.port.in.DeleteEmployeeUseCase;
import com.invex.employee.modulo.application.port.in.UpdateEmployeeUseCase;
import com.invex.employee.modulo.infrastructure.in.rest.dto.CreateEmployeeReq;
import com.invex.employee.modulo.infrastructure.in.rest.dto.EmployeeDTO;
import com.invex.employee.modulo.infrastructure.in.rest.dto.ErrorResponse;
import com.invex.employee.modulo.infrastructure.in.rest.mapper.EmployeeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class ModifyEmployeeController {
    private final AddEmployeeUseCase addEmployeeUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;
    private final EmployeeMapper employeeMapper;

    @Operation(summary = "Create one or more employees.",
            description = "It receives a list contained in the body of the request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "saved correctly."),
            @ApiResponse(responseCode = "400", description = "Incorrect input parameters."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping
    public ResponseEntity<Void> create(@NotNull @Valid @RequestBody CreateEmployeeReq employeeDTO) {
        addEmployeeUseCase.addEmployee(employeeDTO.getEmployees().stream().map(employeeMapper::employeeDTOToModel).toList());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update all or part of the employee data.",
            description = "Employee Information Update.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successfully updated."),
            @ApiResponse(responseCode = "400", description = "Incorrect input parameters."),
            @ApiResponse(responseCode = "404", description = "Employee not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@NotNull @RequestBody EmployeeDTO employeeDTO, @PathVariable String id) {
        updateEmployeeUseCase.updateEmployee(id, employeeMapper.employeeDTOToModel(employeeDTO));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete employee by ID.",
            description = "deletes employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successfully deleted."),
            @ApiResponse(responseCode = "400", description = "Incorrect input parameters."),
            @ApiResponse(responseCode = "404", description = "Employee not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> update(@NotNull @PathVariable String id) {
        deleteEmployeeUseCase.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
