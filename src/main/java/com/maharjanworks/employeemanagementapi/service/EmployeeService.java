package com.maharjanworks.employeemanagementapi.service;

import com.maharjanworks.employeemanagementapi.exception.ResourceNotFoundException;
import com.maharjanworks.employeemanagementapi.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee> findAllEmployees();
    public Employee findEmployeeById(String employeeId);
    public Employee updateEmployee(String employeeId, Employee newEmployeeDetails);
    public Map<String, Boolean> deleteEmployeeById(String employeeId);

 }
