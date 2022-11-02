package com.celso.taskApp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.celso.taskApp.login.models.User;
import com.celso.taskApp.login.repository.UserRepository;
import com.celso.taskApp.task.domain.EPriority;
import com.celso.taskApp.task.domain.Task;
import com.celso.taskApp.task.repository.TaskRepository;
import com.celso.taskApp.task.service.TaskService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    UserRepository userRepository;

    @Autowired
    TaskService taskService;

    @Test
    public void createTaskTest_success() {
        final Task task = new Task("Testing createTask", "This is a test to createTask", EPriority.HIGH, false, 1L);

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        final Task taskdb = taskRepository.save(task);

        assertThat(taskdb).isNotNull();
    }

    @Test
    public void listTastTest() {
        final User user = new User("admin", "admin@test.com", "12345");
        final List<Task> taskList = Arrays.asList(new Task("Testing listTask 1", "This is a test to listTask", EPriority.HIGH, false, 1L),
                new Task("Testing listTask 2", "This is a test to listTask", EPriority.LOW, false, 1L));
        when(userRepository.findById(1l)).thenReturn(Optional.of(Optional.of(user).get()));
        when(taskRepository.findAllByUserId(1L)).thenReturn(taskList);

        final List<Task> tasks = taskRepository.findAllByUserId(1l);
        assertThat(tasks).isNotNull();
        assertThat(tasks.size()).isEqualTo(2);
    }

    @Test
    public void deleteTaskTest() throws Exception {
        final Task task = new Task("Testing delete task", "This is a test to delete task", EPriority.LOW, false, 1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(Optional.of(task).get()));

        taskService.deleteTask(1L);

    }

}
