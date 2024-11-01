package com.inncontrol.task.domain.services;

import com.inncontrol.task.domain.model.aggregates.Task;
import com.inncontrol.task.domain.model.commands.*;

import java.util.Optional;

public interface TaskCommandService {
    Optional<Task> handle(CreateTaskCommand command);

    void handle(StartTaskCommand command);

    void handle(CompleteTaskCommand command);

    void handle(DeleteTaskCommand command);
}
