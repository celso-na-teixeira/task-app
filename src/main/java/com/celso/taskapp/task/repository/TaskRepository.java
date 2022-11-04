package com.celso.taskapp.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celso.taskapp.task.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(Long id);

    Optional<Task> findByIdAndUserId(Long taskID, Long userID);
}
