package com.celso.taskapp.converterTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.celso.taskapp.task.converter.ConvertTaskFormToTask;
import com.celso.taskapp.task.domain.EPriority;
import com.celso.taskapp.task.domain.Task;
import com.celso.taskapp.task.domainform.TaskForm;

@SpringBootTest
class ConvertTaskFormToTaskTest {

    ConvertTaskFormToTask convertTaskFormToTask;

    @BeforeEach
    void setup() {
        convertTaskFormToTask = new ConvertTaskFormToTask();
    }

    @Test
    void convertTaskFormToTaskTest() {
        final LocalDateTime date = LocalDateTime.now();
        final TaskForm taskForm = new TaskForm(null, "Task tittle", "Task description", EPriority.HIGH, false, 1L, date);
        final Task taskOriginal = new Task("Task tittle", "Task description", EPriority.HIGH, false, 1L);
        taskOriginal.setCreatedDt(date);
        final Task taskConverted = convertTaskFormToTask.convert(taskForm);

        assertTrue(taskConverted instanceof Task);
        assertEquals(taskOriginal, taskConverted);

    }

}
