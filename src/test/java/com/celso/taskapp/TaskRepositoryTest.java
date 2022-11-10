package com.celso.taskapp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.celso.taskapp.login.models.User;
import com.celso.taskapp.task.domain.EPriority;
import com.celso.taskapp.task.domain.Priority;
import com.celso.taskapp.task.domain.Task;
import com.celso.taskapp.task.repository.TaskRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @BeforeEach
    void setup() {
        final User john = new User("John", "john@task.com", "123456");
        final User mary = new User("Mary", "mary@task.com", "123456");
        final List<Task> tasks = Arrays.asList(
                new Task("Testing repository", "This is a setup for the repository testing", new Priority(EPriority.MEDIUM), false, john),
                new Task("Testing repo 1", "This is a setup for the repository testing 1", new Priority(EPriority.LOW), false, john),
                new Task("Testing repo 2", "This is a setup for the repository testing 2", new Priority(EPriority.MEDIUM), true, mary),
                new Task("Testing repo 3", "This is a setup for the repository testing 3", new Priority(EPriority.HIGH), false, mary));
        taskRepository.saveAll(tasks);
    }

    @AfterEach
    void destroyAll() {
        taskRepository.deleteAll();
    }

    @Test
    void saveAll_success() {
        final User john = new User("John", "john@task.com", "123456");
        final List<Task> tasks = Arrays.asList(
                new Task("Testing repo 4", "This is a setup for the repository testing 4", new Priority(EPriority.LOW), false, john),
                new Task("Testing repo 5", "This is a setup for the repository testing 5", new Priority(EPriority.MEDIUM), true, john),
                new Task("Testing repo 6", "This is a setup for the repository testing 6", new Priority(EPriority.HIGH), false, john));

        final Iterable<Task> taskIterable = taskRepository.saveAll(tasks);

        final AtomicInteger atomicInteger = new AtomicInteger();

        taskIterable.forEach(task -> {
            if (task.getId() > 0) {
                atomicInteger.getAndIncrement();
            }
        });
        assertThat(atomicInteger.intValue()).isEqualTo(3);
    }

    @Test
    void findByIdAndUserIdTest_success() {
        final Task taskdb = taskRepository.findAll().stream().findAny().orElse(null);
        assertNotNull(taskdb);
        final Task task = taskRepository.findByIdAndUserId(taskdb.getId(), taskdb.getUser().getId()).orElse(null);
        assertThat(taskdb).isEqualTo(task);
    }

    @Test
    void findAllByUserIdTest_success() {
        final List<Task> tasks = taskRepository.findAllByUserId(1L);
        final AtomicInteger atomicInteger = new AtomicInteger();

        tasks.forEach(task -> {
            if (task.getId() > 0) {
                atomicInteger.getAndIncrement();
            }
        });
        assertThat(atomicInteger.intValue()).isEqualTo(2);
    }

}
