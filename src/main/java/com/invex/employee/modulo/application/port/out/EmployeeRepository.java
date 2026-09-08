package com.invex.employee.modulo.application.port.out;

import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.domain.model.PageQuery;
import com.invex.employee.modulo.domain.model.PageResult;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    void saveAll(List<Employee>  employee);

    Optional<Employee> findById(String id);

    List<Employee> findByName(String id);

    void deleteById(String id);

    PageResult<Employee> findAll(PageQuery pageQuery);

}
