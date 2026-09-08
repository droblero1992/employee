package com.invex.employee.modulo.infrastructure.out.persistence.adapter;

import com.invex.employee.modulo.application.port.out.EmployeeRepository;
import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.domain.model.PageQuery;
import com.invex.employee.modulo.domain.model.PageResult;
import com.invex.employee.modulo.infrastructure.out.persistence.EmployeeEntity;
import com.invex.employee.modulo.infrastructure.out.persistence.mapper.MapperEntity;
import com.invex.employee.modulo.infrastructure.out.persistence.repository.JpaEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class EmployeeRepositoryAdapter implements EmployeeRepository {
    private final JpaEmployeeRepository employeeRepository;
    private final MapperEntity mapperEntity;


    @Override
    public void saveAll(List<Employee> employee) {
        List<EmployeeEntity> employeeEntities = employee.stream().map(EmployeeEntity::fromDomain).toList();
        employeeRepository.saveAll(employeeEntities);
    }

    @Override
    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id).map(mapperEntity::entityToModel);
    }

    @Override
    public List<Employee> findByName(String id) {
        return List.of();
    }

    @Override
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public PageResult<Employee> findAll(final PageQuery pageQuery) {
        Page<EmployeeEntity> employePage = employeeRepository.findAll(PageRequest.of(pageQuery.getPage(), pageQuery.getSize()));
        return new PageResult<>(employePage.get().map(mapperEntity::entityToModel).toList(), pageQuery.getPage(), pageQuery.getSize());
    }
}
