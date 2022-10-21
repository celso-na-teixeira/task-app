package com.celso.taskApp.task.service;

import com.celso.taskApp.task.domainForm.TaskForm;

import java.util.List;

public interface TaskService {

    public List<TaskForm> listTask();
    public TaskForm deleteTask(long taskId);
    public TaskForm createTask(TaskForm taskForm);
    public void checkTask(TaskForm taskForm);
    public void unCheckTask(TaskForm taskForm);
}
