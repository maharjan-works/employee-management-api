package com.maharjanworks.employeemanagementapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.maharjanworks.employeemanagementapi.model.Employee;
import com.maharjanworks.employeemanagementapi.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee employee1, employee2;
    List<Employee> employeeList = new ArrayList<>();

    @BeforeEach
     void setup() {
        //arrange
        employee1 = new Employee("EMP1010","Joe","bidden","joe.biden@gmail.com");
        employee2 = new Employee("EMP1011","Kamala","Harris","kamala.harris@gmail.com");
        employeeList.add(employee1);
        employeeList.add(employee2);
    }

    @Test
    void findAllEmployees() throws Exception {
        when(this.employeeService.findAllEmployees()).thenReturn(employeeList);
        this.mockMvc.perform(get("/api/employee")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testFindEmployeeById() throws Exception {
        when(this.employeeService.findEmployeeById("EMP1010")).thenReturn(employee1);
        this.mockMvc.perform(get("/api/employee/EMP1010"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteEmployeeId() throws Exception {
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        when(this.employeeService.deleteEmployeeById("EMP1010")).thenReturn(response);
        this.mockMvc.perform(delete("/api/employee/EMP1010")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testSaveEmployee() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(employee1);

        when(this.employeeService.saveEmployee(employee1)).thenReturn(employee1);
        this.mockMvc.perform(post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void testUpdateEmployee() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(employee1);

        when(this.employeeService.updateEmployee(employee1.getEmployeeId(),employee1)).thenReturn(employee1);
        this.mockMvc.perform(put("/api/employee/EMP1010")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

}
