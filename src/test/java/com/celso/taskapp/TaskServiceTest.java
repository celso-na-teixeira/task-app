package com.celso.taskapp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.celso.taskapp.login.models.User;
import com.celso.taskapp.login.repository.UserRepository;
import com.celso.taskapp.task.domain.EPriority;
import com.celso.taskapp.task.domain.Priority;
import com.celso.taskapp.task.domain.Task;
import com.celso.taskapp.task.repository.TaskRepository;
import com.celso.taskapp.task.service.TaskService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    UserRepository userRepository;

    @Autowired
    TaskService taskService;

    @Test
    void createTaskTest_success() {
        final User mary = new User("Mary", "mary@task.com", "123456");
        final Task task = new Task("Testing createTask", "This is a test to createTask", new Priority(EPriority.HIGH), false, mary);

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        final Task taskdb = taskRepository.save(task);

        assertThat(taskdb).isNotNull();
    }

    @Test
    void listTastTest() {
        final User admin = new User("admin", "admin@test.com", "12345");
        final List<Task> taskList = Arrays.asList(
                new Task("Testing listTask 1", "This is a test to listTask", new Priority(EPriority.HIGH), false, admin),
                new Task("Testing listTask 2", "This is a test to listTask", new Priority(EPriority.LOW), false, admin));
        when(userRepository.findById(1l)).thenReturn(Optional.of(Optional.of(admin).get()));
        when(taskRepository.findAllByUserId(1L)).thenReturn(taskList);

        final List<Task> tasks = taskRepository.findAllByUserId(1l);
        assertThat(tasks).isNotNull();
        assertThat(tasks.size()).isEqualTo(2);
        Assertions.assertEquals(tasks, taskList, "The mock list and the list returned are not equal");
    }

    @Test
    void deleteTaskTest() throws Exception {
        final User mary = new User("Mary", "mary@task.com", "123456");
        final Task task = new Task("Testing delete task", "This is a test to delete task", new Priority(EPriority.LOW), false, mary);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(Optional.of(task).get()));

        taskService.deleteTask(1L);

    }

}