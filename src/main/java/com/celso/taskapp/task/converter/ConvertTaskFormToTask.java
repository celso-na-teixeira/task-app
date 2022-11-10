package com.celso.taskapp.task.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.celso.taskapp.task.domain.Task;
import com.celso.taskapp.task.domainform.TaskForm;

@Component
public class ConvertTaskFormToTask implements Converter<TaskForm, Task> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertTaskFormToTask.class);

    @Override
    public Task convert(final TaskForm form) {
        LOGGER.info("Start convert from TaskForm to Task");
        final Task task = new Task();
        task.setUser(form.getUserId());
        task.setTittle(form.getTittle());
        task.setDescription(form.getDescription());
        task.setPriority(form.getPriority());
        task.setCompleted(form.getCompleted());
        task.setCreatedDt(form.getCreatedDt());
        LOGGER.info("Convert finished");
        return task;
    }
}
