package com.maharjanworks.employeemanagementapi.repository;

import com.maharjanworks.employeemanagementapi.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee1, employee2, savedEmployee;
    private List<Employee> employeeList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        //arrange
        this.employee1 = new Employee("EMP1010", "Joe", "Biden", "joe.biden@gmail.com");
        this.employee2 = new Employee("EMP1011", "Kamala", "Harris", "kamala.harris@gmail.com");
//        this.employeeList.add(employee1);
//        this.employeeList.add(employee2);
    }

    @AfterEach
    void tearDown() {
        this.employee1 = null;
        this.employee2 = null;
        this.employeeList = null;
    }

    @Test
    void testSave(){
        //act
        this.savedEmployee = this.employeeRepository.save(employee1);
        //assert
        assertNotNull(this.savedEmployee);
        assertThat(this.savedEmployee.getEmployeeId()).isEqualTo(this.employee1.getEmployeeId());
    }


    @Test
    public void testFindAll(){
        //+arrange
        this.employeeRepository.save(employee1);
        this.employeeRepository.save((employee2));
        //act
       List<Employee> dbList =  this.employeeRepository.findAll();
       //assert
        assertNotNull(dbList);
        assertThat(dbList.size()).isGreaterThan(0);
        assertThat(employee1.getEmployeeId()).isEqualTo(dbList.get(0).getEmployeeId());
    }

    @Test
    public void testFindById(){
        //+arrange
        this.employeeRepository.save(this.employee1);
        //act
        Employee actualEmployee= this.employeeRepository.findById("EMP1010").get();
        assertNotNull(actualEmployee);
        assertThat(actualEmployee.getEmployeeId()).isEqualTo(this.employee1.getEmployeeId());
    }
}