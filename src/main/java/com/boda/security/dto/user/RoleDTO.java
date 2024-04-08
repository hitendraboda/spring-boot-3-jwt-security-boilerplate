package com.boda.security.dto.user;

import com.boda.security.entity.ERole;
import lombok.Data;

@Data
public class RoleDTO {
    private Integer id;
    private ERole name;
}
