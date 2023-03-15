package com.mockmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockmvc.model.EmployeeVO;
import com.mockmvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@ComponentScan(basePackages = {"com.mockmvc"})
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @Value("${url}")
    String url;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper mapper;

    @PostMapping(value = "/employees")
    public ResponseEntity<EmployeeVO> addEmployee (@Valid @RequestBody EmployeeVO employee)
    {
        System.out.println("Controller URL : "+url);
        service.addEmployee(employee);
        return new ResponseEntity<EmployeeVO>(employee, HttpStatus.CREATED);
    }
}
