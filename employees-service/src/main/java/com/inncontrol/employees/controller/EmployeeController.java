package com.inncontrol.employees.controller;

import com.inncontrol.employees.application.EmployeeService;
import com.inncontrol.employees.domain.Employee;
import com.inncontrol.employees.dto.EmployeeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee Management", description = "Operations pertaining to employee management")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Register a new employee")
    @PostMapping
    public ResponseEntity<Employee> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> existingUsuario = employeeService.findByCorreo(employeeDTO.getCorreo());
        if (existingUsuario.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Employee createdEmployee = employeeService.registerEmployee(employeeDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    @Operation(summary = "Get an employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get all employees")
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @Operation(summary = "Update an employee by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO)
                .map(updatedEmployee -> new ResponseEntity<>(updatedEmployee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete an employee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
