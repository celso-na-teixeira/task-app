package com.celso.taskapp.task.service;

import java.util.List;

import com.celso.taskapp.task.domainform.TaskForm;
import com.celso.taskapp.task.domainform.TaskUpdateRequest;

public interface TaskService {

    List<TaskForm> listTask(Long userId);

    void deleteTask(Long taskId);

    TaskForm createTask(TaskForm taskForm);

    TaskForm checkTask(Long userId, TaskUpdateRequest taskUpdateRequest);
}
