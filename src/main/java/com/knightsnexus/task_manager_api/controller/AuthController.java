package com.knightsnexus.task_manager_api.controller;

import com.knightsnexus.task_manager_api.dto.request.LoginRequestDTO;
import com.knightsnexus.task_manager_api.dto.request.RegisterRequestDTO;
import com.knightsnexus.task_manager_api.dto.response.AuthResponseDTO;
import com.knightsnexus.task_manager_api.dto.response.UserResponseDTO;
import com.knightsnexus.task_manager_api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO requestDTO) {
        UserResponseDTO response = authService.register(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO) {
        AuthResponseDTO response= authService.login(requestDTO);
        return ResponseEntity.ok(response);
    }


}
