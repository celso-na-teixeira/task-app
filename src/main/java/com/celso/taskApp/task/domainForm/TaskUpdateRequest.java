package com.celso.taskApp.task.domainForm;

public class TaskUpdateRequest {
    private final Long taskId;
    private final boolean isCompleted;

    public TaskUpdateRequest(Long taskId, boolean isCompleted) {
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
