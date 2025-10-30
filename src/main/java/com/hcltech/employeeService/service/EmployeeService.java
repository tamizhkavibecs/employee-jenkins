package com.hcltech.employeeService.service;

import com.hcltech.employeeService.dao.service.EmployeeDaoService;
import com.hcltech.employeeService.dto.EmployeeDto;
import com.hcltech.employeeService.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeDaoService employeeDaoService;

    public List<EmployeeDto> getAll(){
        List<Employee> allEmployees = employeeDaoService.getAll();
        List<EmployeeDto> result =  toDto(allEmployees);
        return result;
    }
    public EmployeeDto getOneById(Integer id){
        Employee getOneEmployee = employeeDaoService.getOneById(id);
        EmployeeDto result = toDto(getOneEmployee);
        return result;
    }
    public EmployeeDto create(EmployeeDto employeeDto){
        Employee employee = toEntity(employeeDto);
        Employee savedEmployee =  employeeDaoService.create(employee);
        EmployeeDto result = toDto(savedEmployee);
        return result;
    }
    public EmployeeDto update(Integer id,EmployeeDto employeeDto){
        Employee employee = toEntity(employeeDto);
        Employee updatedEmployee = employeeDaoService.update(employee);
        EmployeeDto result = toDto(updatedEmployee);
        return result;
    }
    public void delete(Integer id){
        employeeDaoService.delete(id);
    }
    /*=================================================*/
    //toEntity and toDto conversion
    private List<EmployeeDto> toDto(List<Employee> employees){
         return employees.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }
    private EmployeeDto toDto(Employee employee){
        if (employee == null){
            return null;
        }
        EmployeeDto result = new EmployeeDto();
        result.setId(employee.getId());
        result.setFirstName(employee.getFirstName());
        result.setLastName(employee.getLastName());
        result.setEmail(employee.getEmail());
        result.setDepartment(employee.getDepartment());

        return result;
    }
    private Employee toEntity(EmployeeDto employeeDto){

        if (employeeDto ==null){
            return null;
        }
        Employee result = new Employee();
        result.setId(employeeDto.getId());
        result.setFirstName(employeeDto.getFirstName());
        result.setLastName(employeeDto.getLastName());
        result.setEmail(employeeDto.getEmail());
        result.setDepartment(employeeDto.getDepartment());
        return result;
    }
}
