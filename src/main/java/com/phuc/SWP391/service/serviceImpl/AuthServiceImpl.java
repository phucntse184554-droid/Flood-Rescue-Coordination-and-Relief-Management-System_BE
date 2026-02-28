package com.phuc.SWP391.service.serviceImpl;

import com.phuc.SWP391.model.entity.User;
import com.phuc.SWP391.model.exception.ApiException;
import com.phuc.SWP391.model.requestDTOS.LoginDto;
import com.phuc.SWP391.model.requestDTOS.SigupDto;
import com.phuc.SWP391.model.responseDTOS.AuthResponse;
import com.phuc.SWP391.repository.UserRepository;
import com.phuc.SWP391.security.JwtTokenProvider;
import com.phuc.SWP391.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service

public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginDto loginDto) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

            User user = userRepository.getUserByEmail(loginDto.getUsername()).orElseThrow(()  -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));

            String accessToken = jwtTokenProvider.generateAccessToken(authentication, user);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication, user);

            AuthResponse response = new AuthResponse();
            response.setAccessToken(accessToken);
            response.setRefreshToken(refreshToken);
            return response;
        }
    catch (BadCredentialsException e) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Username hoac mat khau khong dung");
    }
    }

    @Override
    public String register(SigupDto sigupDto) {
        User exis = userRepository.getUserByEmail(sigupDto.getUsername()).orElseThrow(()->new ApiException(HttpStatus.BAD_REQUEST,"user đã tồn tại"));

            User user = new User();
            user.setUsername(sigupDto.getUsername());
            user.setPassword(passwordEncoder.encode(sigupDto.getPassword()));

            userRepository.save(user);

            return "Register successful!";
        }
    }
