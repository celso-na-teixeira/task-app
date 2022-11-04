package com.celso.taskapp.task.domainform;

public class TaskUpdateRequest {
    private final Long taskId;
    private final boolean isCompleted;

    public TaskUpdateRequest(final Long taskId, final boolean isCompleted) {
        this.taskId = taskId;
        this.isCompleted = isCompleted;
    }

    public Long getTaskId() {
        return taskId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
