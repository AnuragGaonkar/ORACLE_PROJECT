package com.enterprise.ems.service;

import com.enterprise.ems.dto.EmployeeDto;
import com.enterprise.ems.entity.Employee;
import com.enterprise.ems.mapper.EmployeeMapper;
import com.enterprise.ems.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl extends EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeDto createNewEmployee(EmployeeDto employeeDto){
        Employee newEmployee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(newEmployee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
}
