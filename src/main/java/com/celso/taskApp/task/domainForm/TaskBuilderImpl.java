package com.celso.taskApp.task.domainForm;

import com.celso.taskApp.task.domain.EPriority;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskBuilderImpl implements TaskBuilder{

    private Long id;
    private String tittle;
    private String description;
    private EPriority priority;
    private Boolean completed;
    private Long userId;
    private LocalDateTime changedDt;

    private LocalDateTime createdDt;

    @Override
    public TaskBuilder setId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public TaskBuilder setTittle(String tittle) {
        this.tittle=tittle;
        return this;
    }

    @Override
    public TaskBuilder setDescription(String description) {
        this.description=description;
        return this;
    }

    @Override
    public TaskBuilder setPriority(EPriority priority) {
        this.priority=priority;
        return this;
    }

    @Override
    public TaskBuilder setCompleted(Boolean completed) {
        this.completed=completed;
        return this;
    }

    @Override
    public TaskBuilder setUserId(long userId) {
        this.userId=userId;
        return this;
    }

    @Override
    public TaskBuilder setChangedDt(LocalDateTime changedDt) {
        this.changedDt=changedDt;
        return this;
    }

    @Override
    public TaskBuilder setCreatedDt(LocalDateTime createdDt) {
        this.createdDt = createdDt;
        return this;
    }

    public TaskForm build(){
        TaskForm taskForm = new TaskForm();
        if (!Objects.isNull(this.id))
            taskForm.setId(this.id);
        if (!Objects.isNull(this.tittle))
            taskForm.setTittle(this.tittle);
        if (!Objects.isNull(this.description))
            taskForm.setDescription(this.description);
        if (!Objects.isNull(this.completed))
            taskForm.setCompleted(this.completed);
        if (!Objects.isNull(this.priority))
            taskForm.setPriority(this.priority);
        if (!Objects.isNull(this.userId))
            taskForm.setUserId(this.userId);
        if (!Objects.isNull(this.changedDt))
            taskForm.setChangedDt(this.changedDt);
        if (!Objects.isNull(this.createdDt))
            taskForm.setCreatedDt(this.createdDt);

        return taskForm;
    }
}
