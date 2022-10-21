package com.celso.taskApp.task.domainForm;

import com.celso.taskApp.task.domain.Priority;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskForm {

    private Long id;
    private String tittle;
    private String description;
    private Priority priority;
    private Boolean completed;
    private Long userId;
    private LocalDateTime createdDt;
    private LocalDateTime changedDt;

    public TaskForm(String tittle, String description, Priority priority, Boolean completed, Long userId) {
        this.tittle = tittle;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.userId=userId;
        this.createdDt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

    public LocalDateTime getChangedDt() {
        return changedDt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskForm taskForm = (TaskForm) o;
        return Objects.equals(id, taskForm.id) && Objects.equals(tittle, taskForm.tittle) && Objects.equals(description, taskForm.description) && priority == taskForm.priority && Objects.equals(completed, taskForm.completed) && Objects.equals(createdDt, taskForm.createdDt) && Objects.equals(changedDt, taskForm.changedDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tittle, description, priority, completed, createdDt, changedDt);
    }

    @Override
    public String toString() {
        return "TaskForm{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", completed=" + completed +
                ", createdDt=" + createdDt +
                ", changedDt=" + changedDt +
                '}';
    }
}
