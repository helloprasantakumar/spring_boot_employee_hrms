package com.example.emp_hrms.controller;

import com.example.emp_hrms.entity.Employee;
import com.example.emp_hrms.entity.EmployeeDTO;
import com.example.emp_hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/insert-employees-data")
    public List<Employee> insertAllEmployee(@RequestBody List<Employee> employees) {
        return employeeService.insertEmployee(employees);
    }

    @GetMapping("/get-employees-data")
    public List<EmployeeDTO> getAllEmployee() {
        return employeeService.getAllEmployees();
    }




}
