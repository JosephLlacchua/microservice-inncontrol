package com.inncontrol.task.domain.services;

import com.inncontrol.task.domain.model.aggregates.Task;
import com.inncontrol.task.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {
    Optional<Task> handle(GetTaskByIdQuery query);

    List<Task> handle(GetAllTaskQuery query);

}
