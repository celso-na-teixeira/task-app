package com.celso.taskapp.task.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celso.taskapp.login.payload.response.MessageResponse;
import com.celso.taskapp.task.domainform.TaskForm;
import com.celso.taskapp.task.domainform.TaskUpdateRequest;
import com.celso.taskapp.task.error.TaskNotFoundException;
import com.celso.taskapp.task.error.UserNotFoundException;
import com.celso.taskapp.task.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public void setTaskService(final TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createTask(@Valid @RequestBody final TaskForm taskForm) {
        TaskForm taskForm1 = null;
        try {
            taskForm1 = taskService.createTask(taskForm);
        } catch (final UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (final Exception ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(taskForm1);
    }

    @GetMapping("/list/userId={userId}")
    public ResponseEntity<?> listTask(@PathVariable final Long userId) {
        List<TaskForm> taskForms = null;
        try {
            taskForms = taskService.listTask(userId);
        } catch (final UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (final Exception ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(taskForms);
    }

    @DeleteMapping("/delete/taskId={taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable final Long taskId) {
        try {
            taskService.deleteTask(taskId);
        } catch (final TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (final Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(new MessageResponse("Task deleted."));
    }

    @PutMapping("/check/userId={userId}")
    public ResponseEntity<?> checkTask(@PathVariable final Long userId, @RequestBody final TaskUpdateRequest taskUpdateRequest) {
        TaskForm taskForm = null;
        try {
            taskForm = taskService.checkTask(userId, taskUpdateRequest);
        } catch (final TaskNotFoundException taskNotFoundException) {
            return ResponseEntity.badRequest().body(new MessageResponse(taskNotFoundException.getMessage()));
        } catch (final UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(new MessageResponse(userNotFoundException.getMessage()));
        } catch (final Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(exception.getMessage()));
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(taskForm);
    }

    @PutMapping("/uncheck/userId={userId}")
    public ResponseEntity<?> uncheckTask(@PathVariable final Long userId, @RequestBody final TaskUpdateRequest taskUpdateRequest) {
        TaskForm taskForm = null;
        try {
            taskForm = taskService.checkTask(userId, taskUpdateRequest);
        } catch (final TaskNotFoundException taskNotFoundException) {
            return ResponseEntity.badRequest().body(new MessageResponse(taskNotFoundException.getMessage()));
        } catch (final UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(new MessageResponse(userNotFoundException.getMessage()));
        } catch (final Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(exception.getMessage()));
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(taskForm);
    }

}
