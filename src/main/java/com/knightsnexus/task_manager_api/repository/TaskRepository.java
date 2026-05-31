package com.knightsnexus.task_manager_api.repository;

import com.knightsnexus.task_manager_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
