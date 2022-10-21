package com.celso.taskApp.task.domainForm;

import com.celso.taskApp.task.domain.Priority;

import java.time.LocalDateTime;

public class TaskBuilderImpl implements TaskBuilder{

    private Long id;
    private String tittle;
    private String description;
    private Priority priority;
    private Boolean completed;
    private Long userId;
    private LocalDateTime changedDt;

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
    public TaskBuilder setPriority(Priority priority) {
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

    public TaskForm build(){
        return new TaskForm(this.tittle, this.description, this.priority, this.completed, this.userId);
    }
}
