package com.example.emp_hrms.service;

import com.example.emp_hrms.entity.Employee;
import com.example.emp_hrms.entity.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<Employee> insertEmployee(List<Employee> employee);
    List<EmployeeDTO> getAllEmployees();


}
