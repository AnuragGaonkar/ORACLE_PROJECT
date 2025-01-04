package com.enterprise.ems;

import com.enterprise.ems.config.DatabaseInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ApplicationContext context) {
        return args -> {
            // Initialize the database
            DatabaseInitializer databaseInitializer = context.getBean(DatabaseInitializer.class);
            databaseInitializer.initializeDatabase();
        };
    }
}
