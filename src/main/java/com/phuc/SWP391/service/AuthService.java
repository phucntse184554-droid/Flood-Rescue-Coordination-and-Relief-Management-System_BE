package com.phuc.SWP391.service;

import com.phuc.SWP391.model.requestDTOS.LoginDto;
import com.phuc.SWP391.model.requestDTOS.SigupDto;
import com.phuc.SWP391.model.responseDTOS.AuthResponse;

public interface AuthService {
    public AuthResponse login (LoginDto loginDto);
    public String register (SigupDto sigupDto);

}
