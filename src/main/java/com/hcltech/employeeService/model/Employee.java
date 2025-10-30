package com.hcltech.employeeService.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 20,nullable = false)
    private String firstName ;
    @Column(length = 20, nullable = false)
    private String lastName;
    @Column(length = 20, nullable = false)
    private String department;
    @Column(length = 50, nullable = false)
    private String email;


}
