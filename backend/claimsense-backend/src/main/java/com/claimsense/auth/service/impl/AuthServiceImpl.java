package com.claimsense.auth.service.impl;

import com.claimsense.auth.dto.AuthResponse;
import com.claimsense.auth.dto.LoginRequest;
import com.claimsense.auth.dto.RegisterRequest;
import com.claimsense.auth.entity.Role;
import com.claimsense.auth.entity.User;
import com.claimsense.auth.entity.UserRole;
import com.claimsense.auth.entity.UserRoleId;
import com.claimsense.auth.repository.RoleRepository;
import com.claimsense.auth.repository.UserRepository;
import com.claimsense.auth.repository.UserRoleRepository;
import com.claimsense.auth.service.AuthService;
import com.claimsense.common.enums.RoleName;
import com.claimsense.common.enums.UserStatus;
import com.claimsense.common.exception.EmailAlreadyExistsException;
import com.claimsense.common.exception.InvalidCredentialsException;
import com.claimsense.common.exception.RoleNotFoundException;
import com.claimsense.common.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Role customerRole = roleRepository.findByName(RoleName.CUSTOMER)
                .orElseThrow(() -> new RoleNotFoundException("Default role not found"));

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encodedPassword)
                .status(UserStatus.ACTIVE)
                .build();
        User savedUser = userRepository.save(user);

        UserRole userRole = UserRole.builder()
                .id(new UserRoleId(savedUser.getId(), customerRole.getId()))
                .user(savedUser)
                .role(customerRole)
                .build();

        userRoleRepository.save(userRole);
        return AuthResponse.builder()
                .message("User registered successfully")
                .build();
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password");
        }
        return AuthResponse.builder()
                .message("Login successful")
                .build();
    }
}