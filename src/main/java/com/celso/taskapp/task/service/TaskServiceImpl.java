package com.celso.taskapp.task.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.celso.taskapp.login.models.User;
import com.celso.taskapp.login.repository.UserRepository;
import com.celso.taskapp.task.converter.ConvertTaskFormToTask;
import com.celso.taskapp.task.converter.ConvertTaskToTaskForm;
import com.celso.taskapp.task.domain.Task;
import com.celso.taskapp.task.domainform.TaskForm;
import com.celso.taskapp.task.domainform.TaskUpdateRequest;
import com.celso.taskapp.task.error.TaskNotFoundException;
import com.celso.taskapp.task.error.UserNotFoundException;
import com.celso.taskapp.task.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    ConvertTaskFormToTask convertTaskFormToTask;

    @Autowired
    ConvertTaskToTaskForm convertTaskToTaskForm;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<TaskForm> listTask(final Long userId) {
        LOGGER.info("Listing task");
        final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found."));
        final List<Task> tasks = taskRepository.findAllByUserId(user.getId());

        return tasks.stream().map(task -> convertTaskToTaskForm.convert(task)).collect(Collectors.toList());
    }

    @Override
    public void deleteTask(final Long taskId) {
        LOGGER.info("Deleting task");
        final Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task Doesn't exists."));
        taskRepository.deleteById(task.getId());
    }

    @Override
    public TaskForm createTask(final TaskForm taskForm) {
        LOGGER.info("Creating task");
        final Task task = convertTaskFormToTask.convert(taskForm);
        return convertTaskToTaskForm.convert(taskRepository.save(task));
    }

    @Override
    public TaskForm checkTask(final Long userId, final TaskUpdateRequest taskUpdateRequest) {
        LOGGER.info("Checking the task");
        final Task task = taskRepository.findByIdAndUserId(taskUpdateRequest.getTaskId(), userId)
                .orElseThrow(() -> new TaskNotFoundException("The task wasn't found."));
        task.setCompleted(taskUpdateRequest.isCompleted());
        task.setChangedDt(LocalDateTime.now());
        return convertTaskToTaskForm.convert(taskRepository.save(task));
    }

}
