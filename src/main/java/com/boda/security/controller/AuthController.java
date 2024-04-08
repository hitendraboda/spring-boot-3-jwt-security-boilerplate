package com.boda.security.controller;

import com.boda.security.dto.auth.AuthRequestDTO;
import com.boda.security.dto.auth.AuthResponseDTO;
import com.boda.security.dto.user.UserDTO;
import com.boda.security.dto.user.UserRequestDTO;
import com.boda.security.service.AuthService;
import com.boda.security.service.UserService;
import com.boda.security.util.ObjectMapperUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(authService.login(authRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(ObjectMapperUtils.map(userService.createUser(userRequestDTO), UserDTO.class));
    }
}