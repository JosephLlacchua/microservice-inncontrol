package com.inncontrol.task;

import com.inncontrol.task.domain.model.commands.CompleteTaskCommand;
import com.inncontrol.task.domain.model.commands.CreateTaskCommand;
import com.inncontrol.task.domain.model.commands.DeleteTaskCommand;
import com.inncontrol.task.domain.model.commands.StartTaskCommand;
import com.inncontrol.task.domain.model.queries.GetAllTaskQuery;
import com.inncontrol.task.domain.model.queries.GetTaskByIdQuery;
import com.inncontrol.task.domain.services.TaskCommandService;
import com.inncontrol.task.domain.services.TaskQueryService;
import com.inncontrol.task.interfaces.rest.resources.TaskCreateCommandResource;
import com.inncontrol.task.interfaces.rest.resources.TaskResource;
import com.inncontrol.task.interfaces.rest.transforms.TaskResourceFromEntityAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskCommandService taskCommandService;

    @MockBean
    private TaskQueryService taskQueryService;

    @BeforeEach
    public void setUp() {
        Mockito.reset(taskCommandService, taskQueryService);
    }

    @Test
    public void testGetTaskById_NotFound() throws Exception {
        Mockito.when(taskQueryService.handle(any(GetTaskByIdQuery.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/tasks/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllTasks_Empty() throws Exception {
        Mockito.when(taskQueryService.handle(any(GetAllTaskQuery.class))).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testCreateTask_BadRequest() throws Exception {
        // Estructura JSON para una solicitud inválida (ajústalo según el diseño de TaskCreateCommandResource)
        String requestBody = "{\"title\": \"\"}";  // Campo 'title' vacío

        // Asegúrate de que el servicio devuelva Optional.empty() cuando se recibe un comando inválido
        Mockito.when(taskCommandService.handle(any(CreateTaskCommand.class))).thenReturn(Optional.empty());

        // Realiza la solicitud POST y verifica que el estado devuelto sea BadRequest (400)
        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testDeleteTask() throws Exception {
        Mockito.doNothing().when(taskCommandService).handle(any(DeleteTaskCommand.class));

        mockMvc.perform(delete("/api/v1/tasks/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testStartTask() throws Exception {
        Mockito.doNothing().when(taskCommandService).handle(any(StartTaskCommand.class));

        mockMvc.perform(post("/api/v1/tasks/1/start"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCompleteTask() throws Exception {
        Mockito.doNothing().when(taskCommandService).handle(any(CompleteTaskCommand.class));

        mockMvc.perform(post("/api/v1/tasks/1/complete"))
                .andExpect(status().isOk());
    }
}
