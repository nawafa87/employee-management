package com.example.employee_management.service.impl;

import com.example.employee_management.exception.EmployeeNotFoundException;
import com.example.employee_management.exception.InvalidInputException;
import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.service.EmailService;
import com.example.employee_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    // Create employee
    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("Attempting to create employee: {}", employee);

        if (employee.getEmail() == null || !employee.getEmail().contains("@")) {
            logger.error("Invalid email: {}", employee.getEmail());
            throw new InvalidInputException("Invalid email format");
        }

        if (employee.getDepartment() == null || employee.getDepartment().isEmpty()) {
            logger.error("Invalid department: {}", employee.getDepartment());
            throw new InvalidInputException("Invalid department");
        }

        Employee savedEmployee = employeeRepository.save(employee);

        // Send email notification asynchronously
        emailService.sendEmail(savedEmployee.getEmail(), "Welcome to the company", "Hello, " + savedEmployee.getFirstName() + ", welcome to the team!");

        logger.info("Employee created successfully: {}", savedEmployee);
        return savedEmployee;
    }

    // Get employee by ID
    @Override
    public Employee getEmployeeById(UUID id) {
        logger.info("Fetching employee with ID: {}", id);

        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee not found with ID: {}", id);
                    return new EmployeeNotFoundException("Employee not found with ID: " + id);
                });
    }

    // Update employee
    @Override
    public Employee updateEmployee(UUID id, Employee employee) {
        logger.info("Attempting to update employee with ID: {}", id);

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        logger.info("Employee updated successfully: {}", updatedEmployee);
        return updatedEmployee;
    }

    // Delete employee
    @Override
    public void deleteEmployee(UUID id) {
        logger.info("Attempting to delete employee with ID: {}", id);

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        employeeRepository.delete(existingEmployee);
        logger.info("Employee deleted successfully with ID: {}", id);
    }

    // List all employees
    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        return employeeRepository.findAll();
    }
}
