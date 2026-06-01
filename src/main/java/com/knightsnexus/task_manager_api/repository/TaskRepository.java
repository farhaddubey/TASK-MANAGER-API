package com.knightsnexus.task_manager_api.repository;

import com.knightsnexus.task_manager_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByCompleted(Boolean completed, Pageable pageable);

    Page<Task> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Task> findByCompletedAndTitleContainingIgnoreCase(Boolean completed, String keyword, Pageable pageable);
}
