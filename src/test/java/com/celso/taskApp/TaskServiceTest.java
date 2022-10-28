package com.celso.taskApp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
