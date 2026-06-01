package com.knightsnexus.task_manager_api.service;

import com.knightsnexus.task_manager_api.dto.request.TaskRequestDTO;
import com.knightsnexus.task_manager_api.dto.response.TaskResponseDTO;
import com.knightsnexus.task_manager_api.entity.Task;
import com.knightsnexus.task_manager_api.exception.ResourceNotFoundException;
import com.knightsnexus.task_manager_api.repository.TaskRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

//    public TaskService(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }

    public TaskResponseDTO createTask(@Valid TaskRequestDTO requestDTO) {

        Task task = Task.builder()
                .title(requestDTO.getTitle())
                .description(requestDTO.getDescription())
                .completed(false)
                .createdAt(LocalDateTime.now())
                .build();
        Task savedTask = taskRepository.save(task);

        return TaskResponseDTO.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .completed(savedTask.getCompleted())
                .createdAt(savedTask.getCreatedAt())
                .build();
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with  id: " + id));
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.getCompleted())
                .createdAt(task.getCreatedAt())
                .build();
    }

    public Page<TaskResponseDTO> getAllTasks(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Task> taskPage = taskRepository.findAll(pageable);

        return taskPage.map(task ->
                TaskResponseDTO.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .completed(task.getCompleted())
                        .createdAt(task.getCreatedAt())
                        .build()
        ); 
    }

    public Page<TaskResponseDTO> searchTasks(int page, int size, String sortBy, String direction,
                                             Boolean completed, String keyword) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Task> taskPage;

        if (completed != null && keyword != null) {
            taskPage = taskRepository.findByCompletedAndTitleContainingIgnoreCase(
                    completed,
                    keyword,
                    pageable
            );
        } else if (completed != null) {
            taskPage = taskRepository.findByCompleted(completed, pageable);
        } else if (keyword != null) {
            taskPage = taskRepository.findByTitleContainingIgnoreCase(keyword, pageable);
        } else {
            taskPage = taskRepository.findAll(pageable);
        }
        return taskPage.map(task ->
                TaskResponseDTO.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .completed(task.getCompleted())
                        .createdAt(task.getCreatedAt())
                        .build()
        );
    }
}
