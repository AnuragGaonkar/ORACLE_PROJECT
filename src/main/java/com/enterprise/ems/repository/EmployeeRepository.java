package com.enterprise.ems.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.enterprise.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}