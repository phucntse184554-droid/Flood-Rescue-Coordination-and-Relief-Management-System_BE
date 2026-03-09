package com.phuc.SWP391.utils;

import com.phuc.SWP391.model.AccessToken;
import com.phuc.SWP391.repository.AccessTokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final AccessTokenRepo accessTokenRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            AccessToken accessToken = accessTokenRepository.findByToken(token);
            if (accessToken!=null) accessToken.setRevoked(true);
            accessTokenRepository.save(accessToken);
        }
    }
}

