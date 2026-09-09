package com.invex.employee.modulo.infrastructure.in.rest;

import com.invex.employee.modulo.application.port.in.FindByNameUseCase;
import com.invex.employee.modulo.application.port.in.GetEmployeeUseCase;
import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.domain.model.PageQuery;
import com.invex.employee.modulo.domain.model.PageResult;
import com.invex.employee.modulo.infrastructure.in.rest.dto.EmployeeDTO;
import com.invex.employee.modulo.infrastructure.in.rest.dto.EmployeePageResult;
import com.invex.employee.modulo.infrastructure.in.rest.mapper.EmployeeMapper;
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


    @GetMapping
    public ResponseEntity<EmployeePageResult<EmployeeDTO>> getAll(
            @NotNull @RequestParam("page") Integer page,
            @NotNull @RequestParam("size") Integer size) {
        PageResult<Employee> pageResult = getEmployeeUseCase.getAllEmployees(new PageQuery(page, size));
        List<EmployeeDTO> items = getEmployeeDTOS(pageResult);
        return ResponseEntity.ok(
                new EmployeePageResult<>(items, page)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@NotNull @PathVariable String id) {
        Employee employee = getEmployeeUseCase.getEmployeeById(id);
        return ResponseEntity.ok(employeeMapper.employeeModelToDTO(employee));
    }

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
