package com.celso.taskApp.task.converter;

import com.celso.taskApp.task.domain.Task;
import com.celso.taskApp.task.domainForm.TaskForm;
import java.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConvertTaskFormToTask implements Converter<TaskForm, Task> {

  @Override
  public Task convert(TaskForm form) {
    Task task = new Task();
    task.setTittle(form.getTittle());
    task.setDescription(form.getDescription());
    task.setPriority(form.getPriority());
    task.setCompleted(form.getCompleted());
    task.setCreatedDt(LocalDateTime.now());
    return task;
  }
}
