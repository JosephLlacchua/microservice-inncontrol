package com.inncontrol.task;

import com.inncontrol.task.domain.model.aggregates.Task;
import com.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;
import com.inncontrol.task.domain.model.valueobjects.TaskInformation;
import com.inncontrol.task.domain.model.valueobjects.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;
    private TaskInformation taskInformation;
    private EmployeeIdentifier employee;

    @BeforeEach
    void setUp() {
        taskInformation = new TaskInformation("Task Title", "Task Description");
        employee = new EmployeeIdentifier(123L);
        task = new Task(taskInformation, TaskStatus.SCHEDULED, employee, "employee@example.com");
        task.setDueDate(new Date(System.currentTimeMillis() + 86400000)); // 1 day in the future
    }

    @Test
    void testComplete() {
        task.complete();
        assertEquals(TaskStatus.COMPLETED, task.getStatus());
    }

    @Test
    void testStart() {
        task.start();
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void testIsCompleted() {
        task.complete();
        assertTrue(task.isCompleted());
    }

    @Test
    void testIsInProgress() {
        task.start();
        assertTrue(task.isInProgress());
    }

    @Test
    void testIsExpired() {
        task.setDueDate(new Date(System.currentTimeMillis() - 86400000)); // 1 day in the past
        assertTrue(task.isExpired());
    }

    @Test
    void testGetNiceDueDate() {
        String niceDueDate = task.getNiceDueDate();
        assertNotNull(niceDueDate);
        assertFalse(niceDueDate.isEmpty());
    }
}
