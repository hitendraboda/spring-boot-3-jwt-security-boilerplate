package com.boda.security.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDTO {
    @NotBlank(message = "{auth.username.notBlank}")
    private String username;

    @NotBlank(message = "{auth.password.notBlank}")
    private String password;
}
