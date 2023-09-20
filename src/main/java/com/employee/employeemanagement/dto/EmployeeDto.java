package com.employee.employeemanagement.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeDto {

    private String employeeName;

    private String title;

    private String businessUnit;

    private String place;

    private String supervisorId;

    private String competencies;

    private Double salary;

    public EmployeeDto(String employeeName, String title, String businessUnit, String place, String supervisorId, String competencies, Double salary) {
        this.employeeName = employeeName;
        this.title = title;
        this.businessUnit = businessUnit;
        this.place = place;
        this.supervisorId = supervisorId;
        this.competencies = competencies;
        this.salary = salary;
    }
}
