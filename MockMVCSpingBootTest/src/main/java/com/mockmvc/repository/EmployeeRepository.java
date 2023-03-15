package com.mockmvc.repository;

import com.mockmvc.model.EmployeeVO;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    public void saveEmployee(EmployeeVO employeeVO) {
        System.out.println("Repository : "+employeeVO);
    }
}
