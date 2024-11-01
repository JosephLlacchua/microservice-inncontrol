package com.inncontrol.employees.controller;

import com.inncontrol.employees.application.EmployeeService;
import com.inncontrol.employees.domain.Employee;
import com.inncontrol.employees.dto.EmployeeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee savedEmployee = employeeService.registerEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
}
