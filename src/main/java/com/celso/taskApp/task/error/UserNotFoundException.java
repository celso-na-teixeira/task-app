package com.celso.taskApp.task.error;

public class UserNotFoundException extends RuntimeException{

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
