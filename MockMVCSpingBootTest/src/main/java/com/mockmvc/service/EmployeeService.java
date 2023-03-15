package com.mockmvc.service;

import com.mockmvc.model.EmployeeVO;
import com.mockmvc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Value("${url}")
    String url;

    @Autowired
    EmployeeRepository repository;
    public boolean addEmployee(EmployeeVO employee) {
        System.out.println("Employee : "+employee);
        repository.saveEmployee(employee);
        return true;
    }

    public void printUrl() {
        System.out.println("UrL : "+url);
    }
}
