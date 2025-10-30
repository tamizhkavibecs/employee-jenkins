package com.hcltech.employeeService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcltech.employeeService.dto.EmployeeDto;
import com.hcltech.employeeService.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest  {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    EmployeeService employeeService;
    @Test
    void shouldReturnAllEmployees() throws Exception{
        //Arrange
        List<EmployeeDto> expected = dummyEmployee();
        Mockito.when(employeeService.getAll())
                .thenReturn(expected);
        //Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employeeService/v1/employees"))
                .andDo(MockMvcResultHandlers.print())
                //Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()")
                        .value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName")
                        .value("tamizh"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName")
                        .value("kavi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email")
                        .value("tamizhkavibecs@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].department")
                        .value("dev"));
    }
    @Test
    void shouldReturnEmployeeById() throws Exception {
        // Arrange
        EmployeeDto expected = dummyEmployee().get(0);
        Mockito.when(employeeService.getOneById(1)).thenReturn(expected);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employeeService/v1/employees/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("tamizh"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("kavi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("tamizhkavibecs@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("dev"));
    }
    @Test
    void shouldCreateEmployee() throws Exception {
        // Arrange
        EmployeeDto input = dummyEmployee().get(0);
        Mockito.when(employeeService.create(Mockito.any(EmployeeDto.class))).thenReturn(input);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employeeService/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(input)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("tamizh"));
    }
    @Test
    void shouldUpdateEmployee() throws Exception {
        // Arrange
        EmployeeDto updated = dummyEmployee().get(0);
        updated.setDepartment("QA");

        Mockito.when(employeeService.update(Mockito.eq(1),Mockito.any(EmployeeDto.class)))
                .thenReturn(updated);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/api/employeeService/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updated)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("QA"));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        // Arrange
        Mockito.doNothing().when(employeeService).delete(1);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employeeService/v1/employees/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /* ============= */
    private List<EmployeeDto> dummyEmployee(){
        EmployeeDto listOfEmployee = new EmployeeDto();
        listOfEmployee.setId(1);
        listOfEmployee.setFirstName("tamizh");
        listOfEmployee.setLastName("kavi");
        listOfEmployee.setEmail("tamizhkavibecs@gmail.com");
        listOfEmployee.setDepartment("dev");
        return List.of(listOfEmployee);
    }

}
