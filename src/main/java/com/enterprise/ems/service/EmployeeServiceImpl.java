package com.enterprise.ems.service;

import com.enterprise.ems.dto.EmployeeDto;
import com.enterprise.ems.entity.Employee;
import com.enterprise.ems.mapper.EmployeeMapper;
import com.enterprise.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        // Check if email already exists
        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + employeeDto.getEmail());
        }
        // Convert DTO to Entity
        Employee newEmployee = EmployeeMapper.mapToEmployee(employeeDto);

        // Save employee and return the saved entity as DTO
        Employee savedEmployee = employeeRepository.save(newEmployee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
}
