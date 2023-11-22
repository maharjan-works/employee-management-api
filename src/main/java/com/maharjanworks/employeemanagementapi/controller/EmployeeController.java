package com.maharjanworks.employeemanagementapi.controller;

import com.maharjanworks.employeemanagementapi.model.Employee;
import com.maharjanworks.employeemanagementapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        return this.employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> findAllEmployees(){
        return this.employeeService.findAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee findEmployeeById(@PathVariable("employeeId") String employeeId){
        return this.employeeService.findEmployeeById(employeeId);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee){
        return this.employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployeeId(@PathVariable("employeeId") String employeeId){
        return this.employeeService.deleteEmployeeById(employeeId);
    }


}
