package com.hcltech.employeeService.service;

import com.hcltech.employeeService.dao.service.EmployeeDaoService;
import com.hcltech.employeeService.dto.EmployeeDto;
import com.hcltech.employeeService.model.Employee;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeDaoService employeeDaoService;
    @InjectMocks
    private EmployeeService employeeService;

    @Test
//    @Disabled //=> to disable the test
    void shouldReturnAllEmployees(){
        List<Employee> employees =  dummyEmployee();
        Mockito.when(employeeDaoService.getAll())
                .thenReturn(employees);
        List<EmployeeDto> result = employeeService.getAll();

        assertEquals(1, result.size());
        assertEquals("Ross",result.getFirst().getFirstName());
        assertEquals("ross25@gmail.com",result.getFirst().getEmail());
        assertEquals("testing",result.getFirst().getDepartment());
    }
    @Test
    void shouldReturnEmployeeById() throws Exception{
        Employee employee = dummyEmployee().getFirst();
        Mockito.when(employeeDaoService.getOneById(1)).thenReturn(employee);

        EmployeeDto result = employeeService.getOneById(1);
        assertEquals("Ross",result.getFirstName());
        assertEquals("Geller",result.getLastName());
    }
    @Test
    void shouldCreateEmployee(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1);
        employeeDto.setFirstName("Ross");
        employeeDto.setLastName("Geller");
        employeeDto.setEmail("ross25@gmail.com");
        employeeDto.setDepartment("testing");
        Employee employee = dummyEmployee().getFirst();
        Mockito.when(employeeDaoService.create(Mockito.any(Employee.class))).thenReturn(employee);

        EmployeeDto result = employeeService.create(employeeDto);
        assertEquals("Ross",result.getFirstName());
        assertEquals("Geller",result.getLastName());
        assertEquals("testing",result.getDepartment());
    }
    @Test
    void shouldUpdatedEmployee(){
        EmployeeDto updated = new EmployeeDto();
        updated.setDepartment("QA");
        Employee employee = dummyEmployee().getFirst();
        employee.setDepartment("QA");
        Mockito.when(employeeDaoService.update(Mockito.any(Employee.class))).thenReturn(employee);
        EmployeeDto result = employeeService.update(1,updated);

        assertEquals("QA",result.getDepartment());



    }
    @Test
    void shouldDeleteEmployee() {
        doNothing().when(employeeDaoService).delete(1);
        employeeService.delete(1);
        verify(employeeDaoService).delete(1);
    }

    /*----------------*/
    private List<Employee> dummyEmployee(){
        Employee listOfEmployee = new Employee();
        listOfEmployee.setId(1);
        listOfEmployee.setFirstName("Ross");
        listOfEmployee.setLastName("Geller");
        listOfEmployee.setEmail("ross25@gmail.com");
        listOfEmployee.setDepartment("testing");
        return List.of(listOfEmployee);
    }

}
