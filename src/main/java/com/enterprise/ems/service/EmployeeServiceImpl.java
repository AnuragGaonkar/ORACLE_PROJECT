package com.enterprise.ems.service;

import com.enterprise.ems.dto.EmployeeDto;
import com.enterprise.ems.entity.Employee;
import com.enterprise.ems.mapper.EmployeeMapper;
import com.enterprise.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void createNewEmployee(EmployeeDto employeeDto) {
        // Check if email already exists in the database
        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + employeeDto.getEmail());
        }

        // Convert DTO to Entity
        Employee newEmployee = EmployeeMapper.mapToEmployee(employeeDto);

        // Save employee in the database
        employeeRepository.save(newEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        // Fetch all employees from the repository
        List<Employee> employees = employeeRepository.findAll();

        // Convert List<Employee> to List<EmployeeDto> using the mapper
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }
}
