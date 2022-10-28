package com.celso.taskApp.task.domainForm;

import java.time.LocalDateTime;

import com.celso.taskApp.task.domain.EPriority;

public interface TaskBuilder {

    TaskBuilder setId(Long id);

    TaskBuilder setTittle(String tittle);

    TaskBuilder setDescription(String description);

    TaskBuilder setPriority(EPriority priority);

    TaskBuilder setCompleted(Boolean completed);

    TaskBuilder setUserId(long userId);

    TaskBuilder setChangedDt(LocalDateTime changedDt);

    TaskBuilder setCreatedDt(LocalDateTime createdDt);
}
