package com.celso.taskapp.converterTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.celso.taskapp.login.models.User;
import com.celso.taskapp.task.converter.ConvertTaskToTaskForm;
import com.celso.taskapp.task.domain.EPriority;
import com.celso.taskapp.task.domain.Priority;
import com.celso.taskapp.task.domain.Task;
import com.celso.taskapp.task.domainform.TaskForm;

@SpringBootTest
class ConvertTaskToTaskFormTest {

    ConvertTaskToTaskForm convertTaskToTaskForm;

    @BeforeEach
    public void setup() {
        convertTaskToTaskForm = new ConvertTaskToTaskForm();
    }

    @Test
    void convertTaskToTaskFormTest() {
        final LocalDateTime date = LocalDateTime.now();
        final User john = new User("John", "john@task.com", "123456");
        final TaskForm taskForm = new TaskForm(null, "Task tittle", "Task description", new Priority(EPriority.HIGH), false, john, date);
        final Task taskOriginal = new Task("Task tittle", "Task description", new Priority(EPriority.HIGH), false, john);
        taskOriginal.setCreatedDt(date);
        final TaskForm taskFormConverted = convertTaskToTaskForm.convert(taskOriginal);

        assertTrue(taskFormConverted instanceof TaskForm);
        assertTrue(taskForm.equals(taskFormConverted));

    }

}
