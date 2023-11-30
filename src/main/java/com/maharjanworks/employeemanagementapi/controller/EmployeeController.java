package com.maharjanworks.employeemanagementapi.controller;

import com.maharjanworks.employeemanagementapi.model.Employee;
import com.maharjanworks.employeemanagementapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
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
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("employeeId") String employeeId){
        return ResponseEntity.ok(this.employeeService.findEmployeeById(employeeId));
    }

    @PutMapping("/{employeeId}")
/*    public Employee updateEmployee(@RequestBody Employee employee){
        return this.employeeService.updateEmployee(employee);
    }*/
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") String employeeId, @RequestBody Employee newEmployeeDetails){
        return ResponseEntity.ok(this.employeeService.updateEmployee(employeeId,newEmployeeDetails));
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployeeId(@PathVariable("employeeId") String employeeId){
        return this.employeeService.deleteEmployeeById(employeeId);
    }


}
