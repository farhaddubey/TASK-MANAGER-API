package com.knightsnexus.task_manager_api.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;

    private String name;

    private String email;
}

// IN RESPONSE DTO WE NEVER PROVIDE THE PASSWORD WE ALLOW TO CHANGE BUT NEVER MATCH

