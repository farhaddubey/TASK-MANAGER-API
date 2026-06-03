package com.knightsnexus.task_manager_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    @Email(message = "Invalid Email")
    @NotBlank(message = "Eamil is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password; 
}
