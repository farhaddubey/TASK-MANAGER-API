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
}
