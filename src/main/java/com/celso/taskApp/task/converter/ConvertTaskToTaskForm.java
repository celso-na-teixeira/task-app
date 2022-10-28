package com.celso.taskApp.task.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.celso.taskApp.task.domain.Task;
import com.celso.taskApp.task.domainForm.TaskBuilderImpl;
import com.celso.taskApp.task.domainForm.TaskForm;

@Component
public class ConvertTaskToTaskForm implements Converter<Task, TaskForm> {

    @Override
    public TaskForm convert(Task task) {
        TaskBuilderImpl builder = (TaskBuilderImpl) new TaskBuilderImpl().setId(task.getId()).setTittle(task.getTittle())
                .setDescription(task.getDescription()).setPriority(task.getPriority()).setCompleted(task.getCompleted()).setUserId(task.getUserId())
                .setChangedDt(task.getChangedDt()).setCreatedDt(task.getCreatedDt());

        return builder.build();
    }
}
