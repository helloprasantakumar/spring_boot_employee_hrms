package com.example.emp_hrms.impl;

import com.example.emp_hrms.Utility.ProjectUtility;
import com.example.emp_hrms.Utility.TaxCalculatorUtility;
import com.example.emp_hrms.entity.Employee;
import com.example.emp_hrms.entity.EmployeeDTO;
import com.example.emp_hrms.exception.EmployeeDataValidateException;
import com.example.emp_hrms.repository.EmployeeRepository;
import com.example.emp_hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> insertEmployee(List<Employee> employees) {

        List<Employee> employeeList = employees.stream()
                .peek(employee -> {
                    if (employee.getDateOfJoin() == null) {
                        employee.setDateOfJoin(ProjectUtility.getCurrentTimeStamp());
                    } else {
                        try {
                            if (employee.getFirstName() == null) {
                                throw new EmployeeDataValidateException("First Name Can Not Be Null");
                            } else if (employee.getLastName() == null) {
                                throw new EmployeeDataValidateException("Last Name Can Not Be Null");
                            } else if (employee.getEmail() == null || !ProjectUtility.validateEmail(employee.getEmail())) {
                                throw new EmployeeDataValidateException("Email not valid");
                            } else if (employee.getMobileNumber() == null || employee.getMobileNumber().length() != 10) {
                                throw new EmployeeDataValidateException("Mobile number not valid");
                            } else if (employee.getSalary() == null || employee.getSalary() <= 0) {
                                throw new EmployeeDataValidateException("Enter Valid Salary");
                            }

                        } catch (EmployeeDataValidateException e) {
                            throw new RuntimeException(e);
                        }
                    }
                })
                .collect(Collectors.toList());


        return employeeRepository.saveAll(employeeList);

    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> dbEmployees = employeeRepository.findAll();
        List<EmployeeDTO> customEmployeesDatas = dbEmployees.stream()
                .map(employee -> new EmployeeDTO(
                        employee.getEmpId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        TaxCalculatorUtility.employeeTotalSalary(employee.getDateOfJoin(), employee.getSalary()),
                        TaxCalculatorUtility.calculateEmployeeTax(employee.getDateOfJoin(), employee.getSalary()),
                        TaxCalculatorUtility.calculateEmployeeCess(employee.getDateOfJoin(), employee.getSalary())
                ))
                .collect(Collectors.toList());

        return customEmployeesDatas;

    }


}





