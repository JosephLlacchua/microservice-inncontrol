package com.inncontrol.task.domain.model.commands;

import com.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;

public record CreateTaskCommand(
        String title,
        String description,
        EmployeeIdentifier employeeId,
        String employeeEmail
) {
}
