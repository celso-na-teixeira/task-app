package com.celso.taskapp.task.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.celso.taskapp.login.models.User;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tittle;
    private String description;
    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;
    private Boolean completed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime createdDt;
    private LocalDateTime changedDt;

    public Task() {
    }

    public Task(final String tittle, final String description, final Priority priority, final Boolean completed, final User user) {
        this.tittle = tittle;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.user = user;
        this.createdDt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(final Priority priority) {
        this.priority = priority;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(final Boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Task task = (Task) o;

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
        if (!Objects.equals(user, task.user)) {
            return false;
        }
        if (!Objects.equals(createdDt, task.createdDt)) {
            return false;
        }
        return Objects.equals(changedDt, task.changedDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tittle, description, priority, completed, user, createdDt, changedDt);
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", tittle='" + tittle + '\'' + ", description='" + description + '\'' + ", priority=" + priority
                + ", completed=" + completed + ", userId=" + user + ", createdDt=" + createdDt + ", changedDt=" + changedDt + '}';
    }
}
