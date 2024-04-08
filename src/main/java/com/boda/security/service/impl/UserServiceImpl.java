package com.boda.security.service.impl;

import com.boda.security.dto.user.UserRequestDTO;
import com.boda.security.entity.ERole;
import com.boda.security.entity.Role;
import com.boda.security.entity.User;
import com.boda.security.exception.ServiceException;
import com.boda.security.repository.RoleRepository;
import com.boda.security.repository.UserRepository;
import com.boda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserRequestDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ServiceException("Error: Username is already taken!", HttpStatus.CONFLICT.value());
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ServiceException("Error: Email is already in use!", HttpStatus.CONFLICT.value());
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new ServiceException("Error: Role is not found.", HttpStatus.BAD_REQUEST.value()));
        roles.add(userRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
