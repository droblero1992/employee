package com.invex.employee.modulo.infrastructure.out.persistence.adapter;

import com.invex.employee.modulo.domain.model.Employee;
import com.invex.employee.modulo.domain.model.PageQuery;
import com.invex.employee.modulo.domain.model.PageResult;
import com.invex.employee.modulo.infrastructure.out.persistence.EmployeeEntity;
import com.invex.employee.modulo.infrastructure.out.persistence.mapper.MapperEntity;
import com.invex.employee.modulo.infrastructure.out.persistence.repository.JpaEmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryAdapterTest {
    @Mock
    private JpaEmployeeRepository employeeRepository;
    @Mock
    private MapperEntity mapperEntity;
    @InjectMocks
    private EmployeeRepositoryAdapter employeeRepositoryAdapter;

    @Test
    void saveAllTestOk(){
        List<Employee> employees = new ArrayList<>();
        employeeRepositoryAdapter.saveAll(employees);
        Mockito.verify(employeeRepository).saveAll(Mockito.any());
    }

    @Test
    void findByIdTestOk(){
        Mockito.when(mapperEntity.entityToModel(Mockito.any())).thenReturn( new Employee());
        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeEntity()));
        Optional<Employee> opt =employeeRepositoryAdapter.findById("uuid");
        Assertions.assertTrue(opt.isPresent());
    }

    @Test
    void deleteByIdTestOk(){
        employeeRepositoryAdapter.deleteById("uuid");
        Mockito.verify(employeeRepository).deleteById("uuid");
    }

    @Test
    void findAllTestOk(){
        PageQuery pageQuery = new PageQuery(0, 10);
        Page<EmployeeEntity> employeeEntities = Mockito.mock(Page.class);
        Mockito.when(employeeRepository.findAll(Mockito.any())).thenReturn(employeeEntities );
        PageResult<Employee> result= employeeRepositoryAdapter.findAll(pageQuery);
        Assertions.assertNotNull(result);
    }

    @Test
    void findByNameTestOk(){
        PageQuery pageQuery = new PageQuery(0, 10);
        Page<EmployeeEntity> employeeEntities = Mockito.mock(Page.class);
        Mockito.when(employeeRepository.findEmployeesByName(Mockito.any(), Mockito.any())).thenReturn(employeeEntities );
        PageResult<Employee> result= employeeRepositoryAdapter.findEmployeesByName(pageQuery, "denys");
        Assertions.assertNotNull(result);
    }

}
