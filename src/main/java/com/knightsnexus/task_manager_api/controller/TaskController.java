package com.knightsnexus.task_manager_api.controller;

import com.knightsnexus.task_manager_api.dto.request.TaskRequestDTO;
import com.knightsnexus.task_manager_api.dto.response.TaskResponseDTO;
import com.knightsnexus.task_manager_api.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor

public class TaskController {

    private final TaskService taskService;
    // Spring injects service automatically.

    @PostMapping
    public TaskResponseDTO createTask(@Valid @RequestBody TaskRequestDTO requestDTO) {
        return taskService.createTask(requestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        TaskResponseDTO response = taskService.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> getAllTask(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5") int size) {
        Page<TaskResponseDTO> tasks = taskService.getAllTasks(page, size);
        return ResponseEntity.ok(tasks);
    }
}


//{id] : Dynamic URL variable  }
