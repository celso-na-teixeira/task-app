package com.celso.taskapp.task.domainform;

import java.time.LocalDateTime;
import java.util.Objects;

import com.celso.taskapp.task.domain.EPriority;

public class TaskForm {

    private Long id;
    private String tittle;
    private String description;
    private EPriority priority;
    private Boolean completed;
    private Long userId;
    private LocalDateTime createdDt;
    private LocalDateTime changedDt;

    public TaskForm() {
    }

    public TaskForm(final Long id, final String tittle, final String description, final EPriority priority, final Boolean completed, final Long userId, final LocalDateTime changedDt) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.userId = userId;
        this.createdDt = changedDt;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(final String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public EPriority getPriority() {
        return priority;
    }

    public void setPriority(final EPriority priority) {
        this.priority = priority;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(final Boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(final LocalDateTime createdDt) {
        this.createdDt = createdDt;
    }

    public LocalDateTime getChangedDt() {
        return changedDt;
    }

    public void setChangedDt(final LocalDateTime changedDt) {
        this.changedDt = changedDt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final TaskForm taskForm = (TaskForm) o;

        if (!Objects.equals(id, taskForm.id)) {
            return false;
        }
        if (!Objects.equals(tittle, taskForm.tittle)) {
            return false;
        }
        if (!Objects.equals(description, taskForm.description)) {
            return false;
        }
        if (priority != taskForm.priority) {
            return false;
        }
        if (!Objects.equals(completed, taskForm.completed)) {
            return false;
        }
        if (!Objects.equals(userId, taskForm.userId)) {
            return false;
        }
        if (!Objects.equals(createdDt, taskForm.createdDt)) {
            return false;
        }
        return Objects.equals(changedDt, taskForm.changedDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tittle, description, priority, completed, createdDt, changedDt);
    }

    @Override
    public String toString() {
        return "TaskForm{" + "id=" + id + ", tittle='" + tittle + '\'' + ", description='" + description + '\'' + ", priority=" + priority
                + ", completed=" + completed + ", createdDt=" + createdDt + ", changedDt=" + changedDt + '}';
    }
}
