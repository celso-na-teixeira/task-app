package com.celso.taskApp.task.service;

import com.celso.taskApp.task.domainForm.TaskForm;

import com.celso.taskApp.task.domainForm.TaskUpdateRequest;
import java.util.List;

public interface TaskService {

    List<TaskForm> listTask( Long userId) throws Exception;
    void deleteTask(Long taskId) throws Exception;
    TaskForm createTask(TaskForm taskForm) throws Exception;
    TaskForm checkTask( Long userId, TaskUpdateRequest taskUpdateRequest) throws Exception;
}
