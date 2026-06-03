package com.knightsnexus.task_manager_api.service;

import com.knightsnexus.task_manager_api.dto.request.LoginRequestDTO;
import com.knightsnexus.task_manager_api.dto.request.RegisterRequestDTO;
import com.knightsnexus.task_manager_api.dto.response.AuthResponseDTO;
import com.knightsnexus.task_manager_api.dto.response.UserResponseDTO;
import com.knightsnexus.task_manager_api.entity.User;
import com.knightsnexus.task_manager_api.exception.DuplicateResourceException;
import com.knightsnexus.task_manager_api.repository.UserRepository;
import com.knightsnexus.task_manager_api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public UserResponseDTO register(RegisterRequestDTO requestDTO) {
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already registered.");
        }
        User user = User.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .build();
        User savedUser = userRepository.save(user);

        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build(); 
    }

    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getEmail(),
                        requestDTO.getPassword()
                )
        );

        String token = jwtService.generateToken(requestDTO.getEmail());
        return AuthResponseDTO.builder().token(token).build();
    }
}
