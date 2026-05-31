package com.knightsnexus.task_manager_api.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO {

    private Long id;

    private String title;

    private String description;

    private Boolean completed;

    private LocalDateTime createdAt;
}


// DTO IS NOT DB BOUND
// DTO IS FULLY CUSTOMIZABLE
