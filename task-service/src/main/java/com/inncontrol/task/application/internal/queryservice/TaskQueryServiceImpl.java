package com.inncontrol.task.application.internal.queryservice;

import com.inncontrol.task.domain.model.aggregates.Task;
import com.inncontrol.task.domain.model.queries.GetAllTaskQuery;
import com.inncontrol.task.domain.model.queries.GetTaskByIdQuery;
import com.inncontrol.task.domain.services.TaskQueryService;
import com.inncontrol.task.infrastructure.persistence.jpa.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TaskQueryServiceImpl implements TaskQueryService {

    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return taskRepository.findById(query.id());
    }

    @Override
    public List<Task> handle(GetAllTaskQuery query) {
        return taskRepository.findAll();
    }

}
