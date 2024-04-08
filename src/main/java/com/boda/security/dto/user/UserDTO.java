package com.boda.security.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Set<RoleDTO> roles;
}
