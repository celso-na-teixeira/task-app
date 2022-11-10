package com.celso.taskapp.task.domainform;

import java.time.LocalDateTime;
import java.util.Objects;

import com.celso.taskapp.login.models.User;
import com.celso.taskapp.task.domain.Priority;

public class TaskBuilderImpl implements TaskBuilder {

    private Long id;
    private String tittle;
    private String description;
    private Priority priority;
    private Boolean completed;
    private User user;
    private LocalDateTime changedDt;

    private LocalDateTime createdDt;

    @Override
    public TaskBuilder setId(final Long id) {
        this.id = id;
        return this;
    }

    @Override
    public TaskBuilder setTittle(final String tittle) {
        this.tittle = tittle;
        return this;
    }

    @Override
    public TaskBuilder setDescription(final String description) {
        this.description = description;
        return this;
    }

    @Override
    public TaskBuilder setPriority(final Priority priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public TaskBuilder setCompleted(final Boolean completed) {
        this.completed = completed;
        return this;
    }

    @Override
    public TaskBuilder setUserId(final User user) {
        this.user = user;
        return this;
    }

    @Override
    public TaskBuilder setChangedDt(final LocalDateTime changedDt) {
        this.changedDt = changedDt;
        return this;
    }

    @Override
    public TaskBuilder setCreatedDt(final LocalDateTime createdDt) {
        this.createdDt = createdDt;
        return this;
    }

    public TaskForm build() {
        final TaskForm taskForm = new TaskForm();
        if (!Objects.isNull(this.id)) {
            taskForm.setId(this.id);
        }
        if (!Objects.isNull(this.tittle)) {
            taskForm.setTittle(this.tittle);
        }
        if (!Objects.isNull(this.description)) {
            taskForm.setDescription(this.description);
        }
        if (!Objects.isNull(this.completed)) {
            taskForm.setCompleted(this.completed);
        }
        if (!Objects.isNull(this.priority)) {
            taskForm.setPriority(this.priority);
        }
        if (!Objects.isNull(this.user)) {
            taskForm.setUserId(this.user);
        }
        if (!Objects.isNull(this.changedDt)) {
            taskForm.setChangedDt(this.changedDt);
        }
        if (!Objects.isNull(this.createdDt)) {
            taskForm.setCreatedDt(this.createdDt);
        }

        return taskForm;
    }
}
