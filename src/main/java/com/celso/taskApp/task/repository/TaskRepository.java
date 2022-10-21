package com.celso.taskApp.task.repository;

import com.celso.taskApp.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
