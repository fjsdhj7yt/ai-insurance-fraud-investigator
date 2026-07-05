package com.claimsense.auth.controller;

import com.claimsense.auth.dto.AuthResponse;
import com.claimsense.auth.dto.LoginRequest;
import com.claimsense.auth.dto.RegisterRequest;
import com.claimsense.auth.service.AuthService;
import com.claimsense.common.response.ApiResponse;
import com.claimsense.common.response.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        AuthResponse response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseBuilder.success(
                        HttpStatus.CREATED,
                        "User registered successfully",
                        response
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        HttpStatus.OK,
                        "Login successful",
                        response
                )
        );
    }
}