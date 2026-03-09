package com.phuc.SWP391.service.serviceImpl;

import com.phuc.SWP391.model.dto.AuthRequest;
import com.phuc.SWP391.model.dto.AuthResponse;
import com.phuc.SWP391.model.AccessToken;
import com.phuc.SWP391.model.Role;
import com.phuc.SWP391.model.User;
import com.phuc.SWP391.model.exception.ApiException;
import com.phuc.SWP391.model.payload.request.SignupRequest;
import com.phuc.SWP391.repository.AccessTokenRepo;
import com.phuc.SWP391.repository.RoleRepo;
import com.phuc.SWP391.repository.UserRepository;
import com.phuc.SWP391.security.JwtTokenProvider;
import com.phuc.SWP391.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private AccessTokenRepo accessTokenRepo;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private RoleRepo roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, AccessTokenRepo accessTokenRepo, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, RoleRepo roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accessTokenRepo = accessTokenRepo;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse login(AuthRequest loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getPhone(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userRepository.findByPhone(loginDto.getPhone()).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User not found"));

//            if (!user.getIsVerified()) {
//                throw new ApiException(HttpStatus.FORBIDDEN, "Email is not verified. Please verify your email.");
//            }

            String accessToken = jwtTokenProvider.generateAccessToken(authentication, user);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication, user);
            AccessToken token = new AccessToken();
            token.setToken(accessToken);
            token.setUser(user);
            token.setRevoked(false);
            accessTokenRepo.save(token);


            return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();

        } catch (BadCredentialsException e) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Email hoặc mật khẩu không đúng");
        } catch (LockedException e) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Tài khoản đã bị khóa");
        }
    }

    @Override
    public String signup(SignupRequest signupDto) {
        if (userRepository.existsByPhone(signupDto.getPhone())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already exist!");
        }

        User user = modelMapper.map(signupDto, User.class);

        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        Role userRole = roleRepository.findByName("Citizen").orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User Role not found."));
        user.setRole(userRole);
        user.setIsVerified(false);
        user.setAvatar("default");
        User user1 = userRepository.save(user);
        return "User registered successfully!";
    }

}
