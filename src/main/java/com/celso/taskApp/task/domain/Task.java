package com.celso.taskApp.task.domain;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String tittle;
    private String description;
    private Priority priority;
    private Boolean completed;
    private Long userId;
    private LocalDateTime createdDt;
    private LocalDateTime changedDt;

    public Task() {
    }

    public Task(String tittle, String description, Priority priority, Boolean completed, Long userId) {
        this.tittle = tittle;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.userId = userId;
        this.createdDt = LocalDateTime.now();
        this.changedDt = LocalDateTime.now();
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

    public long getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

    public LocalDateTime getChangedDt() {
        return changedDt;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setCreatedDt(LocalDateTime createdDt) {
        this.createdDt = createdDt;
    }

    public void setChangedDt(LocalDateTime changedDt) {
        this.changedDt = changedDt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) && Objects.equals(tittle, task.tittle) && Objects.equals(description, task.description) && priority == task.priority && Objects.equals(completed, task.completed) && Objects.equals(userId, task.userId) && Objects.equals(createdDt, task.createdDt) && Objects.equals(changedDt, task.changedDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tittle, description, priority, completed, userId, createdDt, changedDt);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", completed=" + completed +
                ", userId=" + userId +
                ", createdDt=" + createdDt +
                ", changedDt=" + changedDt +
                '}';
    }
}
