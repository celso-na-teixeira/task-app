package com.celso.taskApp.task.domainForm;

import com.celso.taskApp.task.domain.EPriority;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public TaskForm(Long id, String tittle, String description, EPriority priority, Boolean completed, Long userId, LocalDateTime changedDt) {
        this.id=id;
        this.tittle = tittle;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.userId=userId;
        this.createdDt = changedDt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public EPriority getPriority() {
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

    public void setChangedDt(LocalDateTime changedDt) {
        this.changedDt = changedDt;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(EPriority priority) {
        this.priority = priority;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setCreatedDt(LocalDateTime createdDt) {
        this.createdDt = createdDt;
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
