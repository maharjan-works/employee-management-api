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
    public Employee findEmployeeById(String employeeId)  {
//        if (this.employeeRepository.findById(employeeId).isPresent())
//            return this.employeeRepository.findById(employeeId).get();
//        throw new ResourceNotFoundException("Record not exists");

        return this.employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id: "+ employeeId));
    }

    @Override
    public Employee updateEmployee(String employeeId, Employee newEmployeeDetails) {
        Employee dbEmployee = this.employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id: "+ employeeId));

        dbEmployee.setFirstName(newEmployeeDetails.getFirstName());
        dbEmployee.setLastName(newEmployeeDetails.getLastName());
        dbEmployee.setEmailId(newEmployeeDetails.getEmailId());
        return this.employeeRepository.save(dbEmployee);
    }

    @Override
    public String deleteEmployeeById(String employeeId) {
        this.employeeRepository.deleteById(employeeId);
        return "Success";
    }
}
