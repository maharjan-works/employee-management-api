package com.maharjanworks.employeemanagementapi.service;

import com.maharjanworks.employeemanagementapi.model.Employee;
import com.maharjanworks.employeemanagementapi.repository.EmployeeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    void testSaveEmployee() {
        //arrange
        Employee employee = Employee.builder()
                .employeeId("EMP1010")
                .firstName("joe")
                .lastName("biden")
                .emailId("joe.biden@gmail.com")
                .build();
        when(this.employeeRepository.save(employee)).thenReturn(employee);
        //act
        Employee savedEmployee = this.employeeService.saveEmployee(employee);
        //assert
        assertNotNull(savedEmployee);
        assertThat(employee.getFirstName()).isEqualTo(savedEmployee.getFirstName());

    }

    @Test
    void findAllEmployees() {
        //arrange
        Employee e1 = new Employee("EMP1010","Joe","Biden","joe.biden@gmail.com");
        Employee e2 = new Employee("EMP1011","Kamala","Harris","kamala.harris@gmail.com");
        List<Employee> eList = new ArrayList<>();
        eList.add(e1);
        eList.add(e2);

        when(this.employeeRepository.findAll()).thenReturn(eList);
        //act
        List<Employee> employeeList = this.employeeService.findAllEmployees();
        //assert
        assertNotNull(employeeList);
        assertThat(employeeList.get(0).getEmailId()).isEqualTo(eList.get(0).getEmailId());

    }

    @Test
    void testFindEmployeeById() {
        //arrange
        Employee employee = new Employee("EMP1010","Joe","Biden","joe.biden@gmail.com");
        when(employeeRepository.findById("EMP1010")).thenReturn(Optional.ofNullable(employee));
        //act
        Employee savedEmployee = this.employeeService.findEmployeeById("EMP1010");
        //assert
        assertNotNull(savedEmployee);
        assertThat(savedEmployee).isEqualTo(employee);
    }

    @Test
    void updateEmployee() {
        //arrange
        Employee employee = new Employee("EMP1010", "Joe","Biden","joe.biden@gmail.com");

       when(this.employeeRepository.findById("EMP1010")).thenReturn(Optional.ofNullable(employee));
       when(this.employeeRepository.save(employee)).thenReturn(employee);
       //act
        Employee updateEmployee = this.employeeService.updateEmployee("EMP1010",employee);
        //assert
        assertNotNull(updateEmployee);
    }

    @Test
    void deleteEmployeeById() {
        //arrange
        Employee employee = new Employee("EMP1010","Joe","Biden","joe.biden@gmail.com");
        when(this.employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.ofNullable(employee));
        //act + assert together
        assertAll(()->employeeService.deleteEmployeeById(employee.getEmployeeId()));
    }
}