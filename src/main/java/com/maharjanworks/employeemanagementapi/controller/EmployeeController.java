package com.maharjanworks.employeemanagementapi.controller;

import com.maharjanworks.employeemanagementapi.model.Employee;
import com.maharjanworks.employeemanagementapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(this.employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAllEmployees(){
        return new ResponseEntity<>(this.employeeService.findAllEmployees(),HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("employeeId") String employeeId){
        return ResponseEntity.ok(this.employeeService.findEmployeeById(employeeId));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") String employeeId, @RequestBody Employee newEmployeeDetails){
        return ResponseEntity.ok(this.employeeService.updateEmployee(employeeId,newEmployeeDetails));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeId(@PathVariable("employeeId") String employeeId){
        return ResponseEntity.ok(this.employeeService.deleteEmployeeById(employeeId));
    }


}
