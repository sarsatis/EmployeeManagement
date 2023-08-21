package com.example.gaganemployeemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GaganEmployeeManagementController {

    @GetMapping("/hi")
    public String sayHello(){
        return "Hello";
    }
    
    @GetMapping("/test")
    public String sayGagan(){
        return "gagan";
    }

}
