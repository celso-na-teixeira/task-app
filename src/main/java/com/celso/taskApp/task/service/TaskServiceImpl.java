package com.celso.taskApp.task.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.celso.taskApp.login.models.User;
import com.celso.taskApp.login.repository.UserRepository;
import com.celso.taskApp.task.converter.ConvertTaskFormToTask;
import com.celso.taskApp.task.converter.ConvertTaskToTaskForm;
import com.celso.taskApp.task.domain.Task;
import com.celso.taskApp.task.domainForm.TaskForm;
import com.celso.taskApp.task.domainForm.TaskUpdateRequest;
import com.celso.taskApp.task.error.TaskNotFoundException;
import com.celso.taskApp.task.error.UserNotFoundException;
import com.celso.taskApp.task.repository.TaskRepository;

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
    public List<TaskForm> listTask(final Long userId) throws Exception {
        final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found."));
        final List<Task> tasks = taskRepository.findAllByUserId(user.getId());

        final List<TaskForm> taskForms = tasks.stream().map(task -> convertTaskToTaskForm.convert(task)).collect(Collectors.toList());
        return taskForms;
    }

    @Override
    public void deleteTask(final Long taskId) throws Exception {
        final Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task Doesn't exists."));
        taskRepository.deleteById(task.getId());
    }

    @Override
    public TaskForm createTask(final TaskForm taskForm) {
        final Task task = convertTaskFormToTask.convert(taskForm);
        return convertTaskToTaskForm.convert(taskRepository.save(task));
    }

    @Override
    public TaskForm checkTask(final Long userId, final TaskUpdateRequest taskUpdateRequest) throws Exception {
        final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found."));
        final Task task = taskRepository.findByIdAndUserId(taskUpdateRequest.getTaskId(), userId)
                .orElseThrow(() -> new TaskNotFoundException("The task wasn't found."));
        task.setCompleted(taskUpdateRequest.isCompleted());
        task.setChangedDt(LocalDateTime.now());
        return convertTaskToTaskForm.convert(taskRepository.save(task));
    }

}
