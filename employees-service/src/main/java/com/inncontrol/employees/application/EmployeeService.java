package com.inncontrol.employees.application;

import com.inncontrol.employees.domain.Employee;
import com.inncontrol.employees.domain.EmployeeGender;
import com.inncontrol.employees.dto.EmployeeDTO;
import com.inncontrol.employees.domain.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
