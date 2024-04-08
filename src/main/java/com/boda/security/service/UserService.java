package com.boda.security.service;

import com.boda.security.dto.user.UserRequestDTO;
import com.boda.security.entity.User;

public interface UserService {
    User createUser(UserRequestDTO dto);
}
