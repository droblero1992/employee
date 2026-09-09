package com.invex.employee.modulo.infrastructure.out.persistence.repository;

import com.invex.employee.modulo.infrastructure.out.persistence.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEmployeeRepository extends CrudRepository<EmployeeEntity, String> {
    Page<EmployeeEntity> findAll(Pageable pageable);

    @Query("SELECT em FROM EmployeeEntity em WHERE LOWER(em.firstName) LIKE LOWER(CONCAT('%', :name, '%')) ")
    Page<EmployeeEntity> findEmployeesByName(Pageable pageable, @Param("name") String name);

}
