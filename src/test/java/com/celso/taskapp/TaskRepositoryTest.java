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

import com.celso.taskapp.task.domain.EPriority;
import com.celso.taskapp.task.domain.Task;
import com.celso.taskapp.task.repository.TaskRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @BeforeEach
    void setup() {
        final List<Task> tasks = Arrays.asList(
                new Task("Testing repository", "This is a setup for the repository testing", EPriority.MEDIUM, false, 1L),
                new Task("Testing repo 1", "This is a setup for the repository testing 1", EPriority.LOW, false, 1L),
                new Task("Testing repo 2", "This is a setup for the repository testing 2", EPriority.MEDIUM, true, 2L),
                new Task("Testing repo 3", "This is a setup for the repository testing 3", EPriority.HIGH, false, 2L));
        taskRepository.saveAll(tasks);
    }

    @AfterEach
    void destroyAll() {
        taskRepository.deleteAll();
    }

    @Test
    void saveAll_success() {
        final List<Task> tasks = Arrays.asList(new Task("Testing repo 4", "This is a setup for the repository testing 4", EPriority.LOW, false, 1L),
                new Task("Testing repo 5", "This is a setup for the repository testing 5", EPriority.MEDIUM, true, 1L),
                new Task("Testing repo 6", "This is a setup for the repository testing 6", EPriority.HIGH, false, 1L));

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
        final Task task = taskRepository.findByIdAndUserId(taskdb.getId(), taskdb.getUserId()).orElse(null);
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
