package com.inncontrol.task.interfaces.rest;

import com.inncontrol.task.domain.model.commands.CompleteTaskCommand;
import com.inncontrol.task.domain.model.commands.DeleteTaskCommand;
import com.inncontrol.task.domain.model.commands.StartTaskCommand;
import com.inncontrol.task.domain.model.queries.GetAllTaskQuery;
import com.inncontrol.task.domain.model.queries.GetTaskByIdQuery;
import com.inncontrol.task.domain.services.TaskCommandService;
import com.inncontrol.task.domain.services.TaskQueryService;
import com.inncontrol.task.interfaces.rest.resources.TaskCreateCommandResource;
import com.inncontrol.task.interfaces.rest.resources.TaskResource;
import com.inncontrol.task.interfaces.rest.transforms.CreateTaskCommandFromResourceAssembler;
import com.inncontrol.task.interfaces.rest.transforms.TaskResourceFromEntityAssembler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/tasks", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Tasks", description = "Tasks Management Endpoints")
@AllArgsConstructor
public class TaskController {

    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    @GetMapping("{id}")
    public ResponseEntity<TaskResource> getTaskById(@PathVariable Long id) {
        var query = new GetTaskByIdQuery(id);
        var task = taskQueryService.handle(query);
        return task
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        var command = new DeleteTaskCommand(id);
        taskCommandService.handle(command);
    }

    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllTasks() {
        var query = new GetAllTaskQuery();
        var tasks = taskQueryService.handle(query)
                .stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskResource> createTask(@RequestBody TaskCreateCommandResource resource) {
        var command = CreateTaskCommandFromResourceAssembler.toCommandFromResource(resource);
        var task = taskCommandService.handle(command);
        return task
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("{id}/start")
    public void startTask(@PathVariable Long id) {
        var command = new StartTaskCommand(id);
        taskCommandService.handle(command);
    }

    @PostMapping("{id}/complete")
    public void completeTask(@PathVariable Long id) {
        var command = new CompleteTaskCommand(id);
        taskCommandService.handle(command);
    }

}
