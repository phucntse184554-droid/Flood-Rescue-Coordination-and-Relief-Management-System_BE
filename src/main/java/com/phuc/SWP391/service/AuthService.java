package com.phuc.SWP391.service;

import com.phuc.SWP391.model.dto.AuthRequest;
import com.phuc.SWP391.model.dto.AuthResponse;
import com.phuc.SWP391.model.payload.request.SignupRequest;

public interface AuthService {
    AuthResponse login(AuthRequest loginDto);
    String signup(SignupRequest signupDto);
}
