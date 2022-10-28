package com.celso.taskApp.converterTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.celso.taskApp.task.converter.ConvertTaskFormToTask;
import com.celso.taskApp.task.converter.ConvertTaskToTaskForm;
import com.celso.taskApp.task.domain.EPriority;
import com.celso.taskApp.task.domain.Task;
import com.celso.taskApp.task.domainForm.TaskForm;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class ConvertTaskToTaskFormTest {


  ConvertTaskToTaskForm convertTaskToTaskForm;

  @Before
  public void setup(){
    convertTaskToTaskForm = new ConvertTaskToTaskForm();
  }

  @Test
  public void convertTaskToTaskFormTest(){
    LocalDateTime date = LocalDateTime.now();
    TaskForm taskForm = new TaskForm(null, "Task tittle", "Task description",
        EPriority.HIGH, false, 1L, date);
    Task taskOriginal = new Task("Task tittle", "Task description",
        EPriority.HIGH, false, 1L);
    taskOriginal.setCreatedDt(date);
    TaskForm taskFormConverted = convertTaskToTaskForm.convert(taskOriginal);

    assertTrue(taskFormConverted instanceof TaskForm);
    assertTrue(taskForm.equals(taskFormConverted));

  }

}
