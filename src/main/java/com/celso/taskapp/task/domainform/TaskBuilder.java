package com.celso.taskapp.task.domainform;

import java.time.LocalDateTime;

import com.celso.taskapp.login.models.User;
import com.celso.taskapp.task.domain.Priority;

public interface TaskBuilder {

    TaskBuilder setId(Long id);

    TaskBuilder setTittle(String tittle);

    TaskBuilder setDescription(String description);

    TaskBuilder setPriority(Priority priority);

    TaskBuilder setCompleted(Boolean completed);

    TaskBuilder setUserId(User user);

    TaskBuilder setChangedDt(LocalDateTime changedDt);

    TaskBuilder setCreatedDt(LocalDateTime createdDt);
}
