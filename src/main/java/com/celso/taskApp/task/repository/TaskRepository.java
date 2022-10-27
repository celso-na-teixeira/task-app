package com.celso.taskApp.task.repository;

import com.celso.taskApp.task.domain.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findAllByUserId(Long id);
  Optional<Task> findByIdAndUserId(Long taskID, Long userID);
}
