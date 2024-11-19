package com.inncontrol.task.interfaces.rest.resources;

import com.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;

import java.util.Date;

public record TaskResource(
        Long id,
        String name,
        String description,
        boolean pending,
        Date dueDate,
        EmployeeIdentifier employee,
        String employeeEmail
) {
}
