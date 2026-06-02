package com.knightsnexus.task_manager_api.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTaskRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private Boolean completed;
}
