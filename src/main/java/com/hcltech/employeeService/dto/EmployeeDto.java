package com.hcltech.employeeService.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
}
