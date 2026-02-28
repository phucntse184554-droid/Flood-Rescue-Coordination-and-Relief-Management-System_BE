package com.phuc.SWP391.controller;


import com.phuc.SWP391.model.requestDTOS.SigupDto;
import com.phuc.SWP391.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody SigupDto request) {
        return authService.register(request);
    }
}