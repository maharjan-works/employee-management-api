package com.maharjanworks.employeemanagementapi.service;

import com.maharjanworks.employeemanagementapi.exception.ResourceNotFoundException;
import com.maharjanworks.employeemanagementapi.model.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);
    public List<Employee> findAllEmployees();
    public Employee findEmployeeById(Long employeeId) throws ResourceNotFoundException;
    public Employee updateEmployee(Employee employee);
    public String deleteEmployeeById(Long employeeId);

 }
