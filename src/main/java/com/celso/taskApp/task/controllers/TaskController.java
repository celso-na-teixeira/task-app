package com.celso.taskApp.task.controllers;

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

import com.celso.taskApp.login.payload.response.MessageResponse;
import com.celso.taskApp.task.domainForm.TaskForm;
import com.celso.taskApp.task.domainForm.TaskUpdateRequest;
import com.celso.taskApp.task.error.TaskNotFoundException;
import com.celso.taskApp.task.error.UserNotFoundException;
import com.celso.taskApp.task.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskForm taskForm) {
        TaskForm taskForm1 = null;
        try {
            taskForm1 = taskService.createTask(taskForm);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(taskForm1);
    }

    @GetMapping("/list/userId={userId}")
    public ResponseEntity<?> listTask(@PathVariable Long userId) {
        List<TaskForm> taskForms = null;
        try {
            taskForms = taskService.listTask(userId);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(taskForms);
    }

    @DeleteMapping("/delete/taskId={taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(new MessageResponse("Task deleted."));
    }

    @PutMapping("/check/userId={userId}")
    public ResponseEntity<?> checkTask(@PathVariable Long userId, @RequestBody TaskUpdateRequest taskUpdateRequest) {
        TaskForm taskForm = null;
        try {
            taskForm = taskService.checkTask(userId, taskUpdateRequest);
        } catch (TaskNotFoundException taskNotFoundException) {
            return ResponseEntity.badRequest().body(new MessageResponse(taskNotFoundException.getMessage()));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(new MessageResponse(userNotFoundException.getMessage()));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(exception.getMessage()));
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(taskForm);
    }

    @PutMapping("/uncheck/userId={userId}")
    public ResponseEntity<?> uncheckTask(@PathVariable Long userId, @RequestBody TaskUpdateRequest taskUpdateRequest) {
        TaskForm taskForm = null;
        try {
            taskForm = taskService.checkTask(userId, taskUpdateRequest);
        } catch (TaskNotFoundException taskNotFoundException) {
            return ResponseEntity.badRequest().body(new MessageResponse(taskNotFoundException.getMessage()));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(new MessageResponse(userNotFoundException.getMessage()));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(exception.getMessage()));
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE).body(taskForm);
    }

}
