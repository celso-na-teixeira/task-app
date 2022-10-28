package com.celso.taskApp.converterTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.celso.taskApp.task.converter.ConvertTaskFormToTask;
import com.celso.taskApp.task.domain.EPriority;
import com.celso.taskApp.task.domain.Task;
import com.celso.taskApp.task.domainForm.TaskForm;

@SpringBootTest
public class ConvertTaskFormToTaskTest {

    ConvertTaskFormToTask convertTaskFormToTask;

    @Before
    public void setup() {
        convertTaskFormToTask = new ConvertTaskFormToTask();
    }

    @Test
    public void convertTaskFormToTaskTest() {
        LocalDateTime date = LocalDateTime.now();
        TaskForm taskForm = new TaskForm(null, "Task tittle", "Task description", EPriority.HIGH, false, 1L, date);
        Task taskOriginal = new Task("Task tittle", "Task description", EPriority.HIGH, false, 1L);
        taskOriginal.setCreatedDt(date);
        Task taskConverted = convertTaskFormToTask.convert(taskForm);

        assertTrue(taskConverted instanceof Task);
        assertEquals(taskOriginal, taskConverted);

    }

}
