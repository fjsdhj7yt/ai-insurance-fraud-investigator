package com.claimsense.auth.service;

import com.claimsense.auth.dto.AuthResponse;
import com.claimsense.auth.dto.LoginRequest;
import com.claimsense.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}