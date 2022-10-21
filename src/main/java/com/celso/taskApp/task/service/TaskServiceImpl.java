package com.celso.taskApp.task.service;

import com.celso.taskApp.task.converter.ConvertTaskFormToTask;
import com.celso.taskApp.task.converter.ConvertTaskToTaskForm;
import com.celso.taskApp.task.domain.Task;
import com.celso.taskApp.task.domainForm.TaskForm;
import com.celso.taskApp.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    ConvertTaskFormToTask convertTaskFormToTask;

    @Autowired
    ConvertTaskToTaskForm convertTaskToTaskForm;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<TaskForm> listTask() {
        return null;
    }

    @Override
    public TaskForm deleteTask(long taskId) {
        return null;
    }

    @Override
    public TaskForm createTask(TaskForm taskForm) {
        Task  task = convertTaskFormToTask.convert(taskForm);
        taskRepository.save(task);
        return taskForm;
    }

    @Override
    public void checkTask(TaskForm taskForm) {

    }

    @Override
    public void unCheckTask(TaskForm taskForm) {

    }
}
