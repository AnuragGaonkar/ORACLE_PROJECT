package com.enterprise.ems.controller;

import com.enterprise.ems.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    // Hardcoded database connection info
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/orclpdb";
    private static final String DB_USERNAME = "ems_new";
    private static final String DB_PASSWORD = "ems_password";
    private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";

    // POST endpoint to create new employee
    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto employeeDto) {
        // Manually insert employee into the Oracle database using JDBC
        insertEmployeeIntoDatabase(employeeDto);

        // Return response with HTTP status 200 OK
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    // Method to insert employee data into the database using JDBC
    private void insertEmployeeIntoDatabase(EmployeeDto employeeDto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Load the Oracle JDBC driver
            Class.forName(DB_DRIVER);

            // Establish the connection
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // SQL Insert query
            String insertQuery = "INSERT INTO employees (first_name, last_name, email) VALUES (?, ?, ?)";

            // Prepare statement to insert data
            preparedStatement = connection.prepareStatement(insertQuery);

            // Set values from the employeeDto
            preparedStatement.setString(1, employeeDto.getFirstName());
            preparedStatement.setString(2, employeeDto.getLastName());
            preparedStatement.setString(3, employeeDto.getEmail());

            // Execute the insert query
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
