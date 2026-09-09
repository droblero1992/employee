package com.invex.employee.modulo.infrastructure.in.rest;

import com.invex.employee.modulo.application.port.in.FindByNameUseCase;
import com.invex.employee.modulo.application.port.in.GetEmployeeUseCase;
import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.domain.model.PageQuery;
import com.invex.employee.modulo.domain.model.PageResult;
import com.invex.employee.modulo.infrastructure.in.rest.dto.EmployeeDTO;
import com.invex.employee.modulo.infrastructure.in.rest.dto.EmployeePageResult;
import com.invex.employee.modulo.infrastructure.in.rest.dto.ErrorResponse;
import com.invex.employee.modulo.infrastructure.in.rest.mapper.EmployeeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class GetEmployeeController {
    private final GetEmployeeUseCase getEmployeeUseCase;
    private final EmployeeMapper employeeMapper;
    private final FindByNameUseCase findByNameUseCase;


    @Operation(summary = "List employees with pagination.",
            description = "Returns a list of employees based on the page and page size.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correct query.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeePageResult.class))),
            @ApiResponse(responseCode = "400", description = "Incorrect input parameters."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping
    public ResponseEntity<EmployeePageResult<EmployeeDTO>> getAll(
            @Parameter(description = "Numero de pagina", example = "0", required = true)
            @NotNull @RequestParam("page") Integer page,
            @Parameter(description = "Tamaño de pagina", example = "0", required = true)
            @NotNull @RequestParam("size") Integer size) {
        PageResult<Employee> pageResult = getEmployeeUseCase.getAllEmployees(new PageQuery(page, size));
        List<EmployeeDTO> items = getEmployeeDTOS(pageResult);
        return ResponseEntity.ok(
                new EmployeePageResult<>(items, page)
        );
    }


    @Operation(summary = "Search for employee by ID.",
            description = "Search for an employee according to the ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correct query.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Incorrect input parameters."),
            @ApiResponse(responseCode = "404", description = "Employee not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@NotNull @PathVariable String id) {
        Employee employee = getEmployeeUseCase.getEmployeeById(id);
        return ResponseEntity.ok(employeeMapper.employeeModelToDTO(employee));
    }

    @Operation(summary = "Search for employees by name.",
            description = "Retrieves a paginated list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correct query.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeePageResult.class))),
            @ApiResponse(responseCode = "400", description = "Incorrect input parameters."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/search")
    public ResponseEntity<EmployeePageResult<EmployeeDTO>> searchEmployeeByName(
            @NotNull @RequestParam("name") final String name,
            @NotNull @RequestParam("page") final Integer page,
            @NotNull @RequestParam("size") final Integer size) {
        PageResult<Employee> response = findByNameUseCase.getEmployeesByName(new PageQuery(page, size), name);
        return ResponseEntity.ok(new EmployeePageResult<>(getEmployeeDTOS(response), page));
    }

    private List<EmployeeDTO> getEmployeeDTOS(PageResult<Employee> pageResult) {
        List<Employee> employees = pageResult.getContent();
        return Optional.of(employees)
                .map(emp -> emp.stream()
                        .map(employeeMapper::employeeModelToDTO))
                .orElse(Stream.<EmployeeDTO>builder().build()).toList();

    }
}
