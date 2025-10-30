package com.hcltech.employeeService.controller;

import com.hcltech.employeeService.dto.EmployeeDto;
import com.hcltech.employeeService.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employeeService/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll(EmployeeDto employeeDto){
        List<EmployeeDto> result = employeeService.getAll();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getOneById(@PathVariable("id") Integer id){
        EmployeeDto result = employeeService.getOneById(id);
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto){
        EmployeeDto result = employeeService.create(employeeDto);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable("id")Integer id,@RequestBody EmployeeDto employeeDto){
        EmployeeDto result = employeeService.update(id,employeeDto);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        employeeService.delete(id);
        return null;
    }
}
