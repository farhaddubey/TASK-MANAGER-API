package com.knightsnexus.task_manager_api.controller;

import com.knightsnexus.task_manager_api.dto.request.TaskRequestDTO;
import com.knightsnexus.task_manager_api.dto.request.UpdateTaskRequestDTO;
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

    @GetMapping("/search")
    public ResponseEntity<Page<TaskResponseDTO>> searchtasks(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(defaultValue = "createdAt") String sortBy,
                                                             @RequestParam(defaultValue = "desc") String direction,
                                                             @RequestParam(required = false) Boolean completed,
                                                             @RequestParam(required = false) String keyword
    ) {
        Page<TaskResponseDTO> tasks = taskService.searchTasks(
                page,
                size,
                sortBy,
                direction,
                completed,
                keyword
        );
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequestDTO requestDTO) {
        TaskResponseDTO response = taskService.updateTask(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task Deleted successfully");
    }
}


//{id] : Dynamic URL variable  }
