package com.enterprise.ems.controller;

import com.enterprise.ems.dto.EmployeeDto;
import com.enterprise.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")  // Allow frontend at http://localhost:3000 to make requests
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // POST endpoint to create new employee
    @PostMapping
    public ResponseEntity<String> createNewEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.createNewEmployee(employeeDto);  // Delegate to the service layer
            return new ResponseEntity<>("Employee created successfully!", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);  // Handle duplicate email
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the employee.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET endpoint to retrieve list of all employees
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        try {
            List<EmployeeDto> employees = employeeService.getAllEmployees();  // Fetch the list of employees
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  // Handle any error
        }
    }
}
