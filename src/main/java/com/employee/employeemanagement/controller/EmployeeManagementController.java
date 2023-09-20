package com.employee.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employeemanagement.dto.EmployeeDto;
import com.employee.employeemanagement.model.Employee;
import com.employee.employeemanagement.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeManagementController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/hi")
    public String sayHello(){
        return "Hello";
    }
    
    @GetMapping("/test")
    public String sayGagan(){
        return "gagan";
    }

    @GetMapping("/sa")
    public String saySa(){
        return "sa";
    }

    

    @PutMapping("/employees/place/{place}/salary/{percentage}")
    public List<EmployeeDto> updateSalaryPercentage(
            @PathVariable("place") String place,
            @PathVariable("percentage") Double percentage) {

        List<Employee> employeesByPlace = employeeRepository.findByPlace(place);

        List<EmployeeDto> employeeDtoList = new ArrayList<>();


        for (Employee employee : employeesByPlace) {
            Double updatedSalary = employee.getSalary() + ((employee.getSalary() * percentage) / 100);

            EmployeeDto employeeDto = new EmployeeDto(employee.getEmployeeName(), employee.getTitle(), employee.getBusinessUnit(),
                    employee.getPlace(), employee.getSupervisorId(), employee.getCompetencies(), updatedSalary);

            employeeDtoList.add(employeeDto);
        }

        return employeeDtoList;
    }


    
}
