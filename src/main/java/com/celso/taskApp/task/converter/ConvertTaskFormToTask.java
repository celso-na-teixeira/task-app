package com.celso.taskApp.task.converter;

import com.celso.taskApp.task.domain.Task;
import com.celso.taskApp.task.domainForm.TaskForm;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConvertTaskFormToTask implements Converter<TaskForm, Task> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConvertTaskFormToTask.class);

  @Override
  public Task convert(TaskForm form) {
    Task task = new Task();
    task.setUserId(form.getUserId());
    task.setTittle(form.getTittle());
    task.setDescription(form.getDescription());
    task.setPriority(form.getPriority());
    task.setCompleted(form.getCompleted());
    task.setCreatedDt(LocalDateTime.now());
    return task;
  }
}
