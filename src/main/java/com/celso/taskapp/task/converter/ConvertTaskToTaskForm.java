package com.celso.taskapp.task.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.celso.taskapp.task.domain.Task;
import com.celso.taskapp.task.domainform.TaskBuilderImpl;
import com.celso.taskapp.task.domainform.TaskForm;

@Component
public class ConvertTaskToTaskForm implements Converter<Task, TaskForm> {

    @Override
    public TaskForm convert(final Task task) {
        final TaskBuilderImpl builder = (TaskBuilderImpl) new TaskBuilderImpl().setId(task.getId()).setTittle(task.getTittle())
                .setDescription(task.getDescription()).setPriority(task.getPriority()).setCompleted(task.getCompleted()).setUserId(task.getUser())
                .setChangedDt(task.getChangedDt()).setCreatedDt(task.getCreatedDt());

        return builder.build();
    }
}
