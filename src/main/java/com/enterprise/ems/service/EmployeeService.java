package com.enterprise.ems.service;

import com.enterprise.ems.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {
    void createNewEmployee(EmployeeDto employeeDto);  // Create a new employee
    List<EmployeeDto> getAllEmployees();  // Retrieve all employees
}
