package com.inncontrol.task.application.internal.commandservice;

import com.inncontrol.task.domain.model.aggregates.Task;
import com.inncontrol.task.domain.model.commands.CompleteTaskCommand;
import com.inncontrol.task.domain.model.commands.CreateTaskCommand;
import com.inncontrol.task.domain.model.commands.DeleteTaskCommand;
import com.inncontrol.task.domain.model.commands.StartTaskCommand;
import com.inncontrol.task.domain.model.valueobjects.TaskInformation;
import com.inncontrol.task.domain.model.valueobjects.TaskStatus;
import com.inncontrol.task.domain.services.TaskCommandService;
import com.inncontrol.task.infrastructure.persistence.jpa.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskCommandServiceImpl implements TaskCommandService {
    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> handle(CreateTaskCommand command) {
        var task = new Task(
                new TaskInformation(command.title(), command.description()),
                TaskStatus.SCHEDULED,
                command.employeeId(),
                command.employeeEmail()
        );
        return Optional.of(taskRepository.save(task));
    }

    @Override
    public void handle(StartTaskCommand command) {
        var task = taskRepository.findById(command.id());
        if (task.isEmpty()) {
            throw new IllegalArgumentException("Task with id not found");
        }
        var taskObject = task.get();
        taskObject.start();
        taskRepository.save(taskObject);
    }

    @Override
    public void handle(CompleteTaskCommand command) {
        var task = taskRepository.findById(command.id());
        if (task.isEmpty()) {
            throw new IllegalArgumentException("Task with id not found");
        }
        var taskObject = task.get();
        taskObject.complete();
        taskRepository.save(taskObject);
    }

    @Override
    public void handle(DeleteTaskCommand command) {
        taskRepository.deleteById(command.id());
    }
}
