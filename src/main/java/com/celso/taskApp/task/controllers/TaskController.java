package com.celso.taskApp.task.controllers;

import com.celso.taskApp.task.domainForm.TaskForm;
import com.celso.taskApp.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService){
        this.taskService=taskService;
    }

    @PostMapping("/task/new")
    public TaskForm createTask(@RequestBody TaskForm taskForm){
        return taskService.createTask(taskForm);
    }

}
