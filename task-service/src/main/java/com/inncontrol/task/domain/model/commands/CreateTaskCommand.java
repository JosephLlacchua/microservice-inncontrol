package com.inncontrol.task.domain.model.commands;

import com.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;

import java.util.Date;

public record CreateTaskCommand(
        String title,
        String description,
        String employeeEmail,
        EmployeeIdentifier employeeId,
        Date dueDate
) {
}
