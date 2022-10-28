package com.celso.taskApp.task.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tittle;
    private String description;
    private EPriority priority;
    private Boolean completed;
    private Long userId;
    private LocalDateTime createdDt;
    private LocalDateTime changedDt;

    public Task() {
    }

    public Task(String tittle, String description, EPriority priority, Boolean completed, Long userId) {
        this.tittle = tittle;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.userId = userId;
        this.createdDt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EPriority getPriority() {
        return priority;
    }

    public void setPriority(EPriority priority) {
        this.priority = priority;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(LocalDateTime createdDt) {
        this.createdDt = createdDt;
    }

    public LocalDateTime getChangedDt() {
        return changedDt;
    }

    public void setChangedDt(LocalDateTime changedDt) {
        this.changedDt = changedDt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        if (!Objects.equals(id, task.id)) {
            return false;
        }
        if (!Objects.equals(tittle, task.tittle)) {
            return false;
        }
        if (!Objects.equals(description, task.description)) {
            return false;
        }
        if (priority != task.priority) {
            return false;
        }
        if (!Objects.equals(completed, task.completed)) {
            return false;
        }
        if (!Objects.equals(userId, task.userId)) {
            return false;
        }
        if (!Objects.equals(createdDt, task.createdDt)) {
            return false;
        }
        return Objects.equals(changedDt, task.changedDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tittle, description, priority, completed, userId, createdDt, changedDt);
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", tittle='" + tittle + '\'' + ", description='" + description + '\'' + ", priority=" + priority
                + ", completed=" + completed + ", userId=" + userId + ", createdDt=" + createdDt + ", changedDt=" + changedDt + '}';
    }
}
