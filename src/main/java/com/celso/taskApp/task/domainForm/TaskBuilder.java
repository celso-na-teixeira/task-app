package com.celso.taskApp.task.domainForm;

import com.celso.taskApp.task.domain.Priority;

import java.time.LocalDateTime;

public interface TaskBuilder {

    public TaskBuilder setId(Long id);
    public TaskBuilder setTittle(String tittle);
    public TaskBuilder setDescription(String description);
    public TaskBuilder setPriority(Priority priority);
    public TaskBuilder setCompleted(Boolean completed);

    public TaskBuilder setUserId(long userId);
    public TaskBuilder setChangedDt(LocalDateTime changedDt);
}
