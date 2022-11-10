package com.celso.taskapp.converterTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.celso.taskapp.login.models.User;
import com.celso.taskapp.task.converter.ConvertTaskFormToTask;
import com.celso.taskapp.task.domain.EPriority;
import com.celso.taskapp.task.domain.Priority;
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
        final User mary = new User("Mary", "mary@task.com", "123456");
        final TaskForm taskForm = new TaskForm(null, "Task tittle", "Task description", new Priority(EPriority.HIGH), false, mary, date);
        final Task taskOriginal = new Task("Task tittle", "Task description", new Priority(EPriority.HIGH), false, mary);
        taskOriginal.setCreatedDt(date);
        final Task taskConverted = convertTaskFormToTask.convert(taskForm);

        assertTrue(taskConverted instanceof Task);
        assertEquals(taskOriginal, taskConverted);

    }

}
