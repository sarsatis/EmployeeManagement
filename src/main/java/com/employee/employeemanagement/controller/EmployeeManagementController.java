package com.employee.employeemanagement.controller;

import com.employee.employeemanagement.service.EmployeeService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employeemanagement.dto.EmployeeDto;
import com.employee.employeemanagement.model.Employee;
import com.employee.employeemanagement.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeManagementController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private Job job;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/hi")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/bye")
    public String sayBye(){
        return "Bye";
    }

    @GetMapping("/sarthak")
    public String saySarthak(){
        return "Sarthak";
    }
    

    @PostMapping("/importEmployees")
    public void importCsvToDBJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getemployees")
    public List<Employee> getEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getbybusinessunit/{businessunit}")
    public List<Employee> getByBusinessUnit(@PathVariable("businessunit") String businessUnit) {
        return employeeService.findByBusinessUnit(businessUnit);
    }

    @GetMapping("/getbyplace/{place}")
    public List<Employee> getByPlace(@PathVariable("place") String place) {
        return employeeService.findByPlace(place);
    }

    @PutMapping("/employee/place/{place}/salary/{percentage}")
    public List<Employee> updateSalaryByPlace(@PathVariable("place") String place, @PathVariable("percentage") Double percentage) {
        return employeeService.updateSalaryByPlace(place, percentage);
    }

    @PutMapping("/employee/title/{title}/place/{place}")
    public List<Employee> updatePlaceByTitle(@PathVariable("title") String title, @PathVariable("place") String place) {
        return employeeService.updatePlaceByTitle(title, place);
    }

    @PostMapping("/employee/insert")
    public Employee insertNewEmployee(@RequestBody Employee employee){
        return employeeService.insertEmployee(employee);
    }

    @DeleteMapping("/employee/delete/{id}")
    public void removeAnEmployee(@PathVariable("id") Integer id){
        employeeService.removeAnEmployee(id);
    }

    @GetMapping("/employee/getsalrangebytitle/{title}")
    public EmployeeDto getRangeOfSalaryByTitle(@PathVariable("title") String title) {
         return employeeService.getRangeOfSalaryByTitle(title);
    }
}
