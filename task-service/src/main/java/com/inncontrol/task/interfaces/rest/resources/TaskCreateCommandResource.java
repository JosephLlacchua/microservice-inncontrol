package com.inncontrol.task.interfaces.rest.resources;

import com.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;

public record TaskCreateCommandResource(
        String employeeEmail,
        String title,
        String description,
        EmployeeIdentifier employee
) {
}
