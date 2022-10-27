package com.celso.taskApp.task.service;

import com.celso.taskApp.login.models.User;
import com.celso.taskApp.login.repository.UserRepository;
import com.celso.taskApp.login.services.UserDetailsImpl;
import com.celso.taskApp.task.converter.ConvertTaskFormToTask;
import com.celso.taskApp.task.converter.ConvertTaskToTaskForm;
import com.celso.taskApp.task.domain.Task;
import com.celso.taskApp.task.domainForm.TaskForm;
import com.celso.taskApp.task.domainForm.TaskUpdateRequest;
import com.celso.taskApp.task.error.TaskNotFoundException;
import com.celso.taskApp.task.error.UserNotFoundException;
import com.celso.taskApp.task.repository.TaskRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
  public List<TaskForm> listTask(Long userId) throws Exception {
    User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found."));
    List<Task> tasks = taskRepository.findAllByUserId(user.getId());

    List<TaskForm> taskForms = tasks.stream().map(task -> convertTaskToTaskForm.convert(task))
        .collect(
            Collectors.toList());
    return taskForms;
  }

  @Override
  public void deleteTask(Long taskId) throws Exception {
    Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task Doesn't exists."));
    taskRepository.deleteById(task.getId());
  }

    @Override
    public TaskForm createTask(TaskForm taskForm) {
        Task  task = convertTaskFormToTask.convert(taskForm);
        return convertTaskToTaskForm.convert(taskRepository.save(task));
    }

  @Override
  public TaskForm checkTask(Long userId, TaskUpdateRequest taskUpdateRequest) throws Exception {
    User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found."));
    Task task = taskRepository.findByIdAndUserId(taskUpdateRequest.getTaskId(), userId).orElseThrow(() -> new TaskNotFoundException("The task wasn't found."));
    task.setCompleted(taskUpdateRequest.isCompleted());
    task.setChangedDt(LocalDateTime.now());
    return convertTaskToTaskForm.convert(taskRepository.save(task));
  }

}
