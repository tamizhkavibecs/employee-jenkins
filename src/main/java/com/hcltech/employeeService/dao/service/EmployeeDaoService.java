package com.hcltech.employeeService.dao.service;

import com.hcltech.employeeService.model.Employee;
import com.hcltech.employeeService.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class EmployeeDaoService {
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll(){

        return employeeRepository.findAll();
    }
    public Employee getOneById(Integer id){

        return employeeRepository.findById(id).orElse(null);
    }
    public Employee create(Employee employee){
        return employeeRepository.save(employee);
    }
    public Employee update(Employee employee){
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employee.getId());

        if (existingEmployeeOptional.isPresent()){
            Employee existingEmployee = existingEmployeeOptional.get();

            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(existingEmployee);
        }else {
            return null;
        }
    }
    public void delete(Integer id){

        employeeRepository.deleteById(id);
    }
}
