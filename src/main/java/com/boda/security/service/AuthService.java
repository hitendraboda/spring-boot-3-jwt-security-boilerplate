package com.boda.security.service;

import com.boda.security.dto.auth.AuthRequestDTO;
import com.boda.security.dto.auth.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO dto);
}
