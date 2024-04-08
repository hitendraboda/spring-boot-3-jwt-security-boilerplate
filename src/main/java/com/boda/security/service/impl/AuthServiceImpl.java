package com.boda.security.service.impl;

import com.boda.security.config.JwtUtils;
import com.boda.security.dto.auth.AuthRequestDTO;
import com.boda.security.dto.auth.AuthResponseDTO;
import com.boda.security.repository.RoleRepository;
import com.boda.security.repository.UserRepository;
import com.boda.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthResponseDTO login(AuthRequestDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return AuthResponseDTO.builder()
                .token(jwtUtils.generateJwtToken(authentication))
                .tokenType("Bearer")
                .expiresIn(jwtUtils.getTokenValidityInSecond())
                .build();
    }
}
