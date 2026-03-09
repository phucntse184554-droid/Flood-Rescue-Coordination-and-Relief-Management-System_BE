package com.phuc.SWP391.controller;

import com.phuc.SWP391.model.dto.AuthRequest;
import com.phuc.SWP391.model.dto.AuthResponse;
import com.phuc.SWP391.model.payload.request.SignupRequest;
import com.phuc.SWP391.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        AuthResponse resp = authService.login(req);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) {
        String result = authService.signup(signupRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
