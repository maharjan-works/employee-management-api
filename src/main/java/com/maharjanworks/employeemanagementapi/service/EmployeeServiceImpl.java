package com.maharjanworks.employeemanagementapi.service;

import com.maharjanworks.employeemanagementapi.exception.ResourceNotFoundException;
import com.maharjanworks.employeemanagementapi.model.Employee;
import com.maharjanworks.employeemanagementapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long employeeId) throws ResourceNotFoundException {
        if (this.employeeRepository.findById(employeeId).isPresent())
            return this.employeeRepository.findById(employeeId).get();
        throw new ResourceNotFoundException("No Record Exists.");
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public String deleteEmployeeById(Long employeeId) {
        this.employeeRepository.deleteById(employeeId);
        return "Success";
    }
}
