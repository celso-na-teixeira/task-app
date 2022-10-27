package com.celso.taskApp.task.error;

public class TaskNotFoundException extends RuntimeException{

  public TaskNotFoundException() {
    super();
  }

  public TaskNotFoundException(String message) {
    super(message);
  }
}
