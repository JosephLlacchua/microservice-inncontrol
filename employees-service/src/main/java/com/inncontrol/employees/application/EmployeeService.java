package com.inncontrol.employees.application;

import com.inncontrol.employees.domain.Employee;
import com.inncontrol.employees.domain.EmployeeGender;
import com.inncontrol.employees.dto.EmployeeDTO;
import com.inncontrol.employees.domain.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee registerEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setNombre(employeeDTO.getNombre());
        employee.setApellido(employeeDTO.getApellido());
        employee.setGenero(EmployeeGender.valueOf(employeeDTO.getGenero()));
        employee.setCorreo(employeeDTO.getCorreo());
        employee.setTelefono(employeeDTO.getTelefono());
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Optional<Employee> updateEmployee(Long id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setNombre(employeeDTO.getNombre());
            employee.setApellido(employeeDTO.getApellido());
            employee.setGenero(EmployeeGender.valueOf(employeeDTO.getGenero()));
            employee.setCorreo(employeeDTO.getCorreo());
            employee.setTelefono(employeeDTO.getTelefono());
            return employeeRepository.save(employee);
        });
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}