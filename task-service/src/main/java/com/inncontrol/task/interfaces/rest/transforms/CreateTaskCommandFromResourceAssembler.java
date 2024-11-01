package com.inncontrol.task.interfaces.rest.transforms;

import com.inncontrol.task.domain.model.commands.CreateTaskCommand;
import com.inncontrol.task.interfaces.rest.resources.TaskCreateCommandResource;

import java.time.Instant;
import java.util.Date;

public class CreateTaskCommandFromResourceAssembler {
    private static Date parseDate(String date) {
        return Date.from(Instant.parse(date));
    }

    public static CreateTaskCommand toCommandFromResource(TaskCreateCommandResource resource) {

        return new CreateTaskCommand(
                resource.title(),
                resource.description(),
                resource.employeeEmail(),
                resource.employee(),
                parseDate(resource.dueDate())
        );
    }
}
