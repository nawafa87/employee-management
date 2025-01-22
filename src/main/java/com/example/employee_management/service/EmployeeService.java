package com.example.employee_management.service;

import com.example.employee_management.model.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    List<Employee> getAllEmployees();

//    List<Employee> getEmployeesByDepartment(String department);

    // New method for creating an employee with validation
    Employee createEmployee(Employee employee);

    Employee getEmployeeById(UUID id);

    Employee updateEmployee(UUID id, Employee employee);

    void deleteEmployee(UUID id);

}
