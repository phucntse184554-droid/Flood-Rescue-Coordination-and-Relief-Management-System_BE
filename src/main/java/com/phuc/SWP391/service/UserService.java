package com.phuc.SWP391.service;

import com.phuc.SWP391.model.entity.User;
import com.phuc.SWP391.model.responseDTOS.AuthResponse;

public interface UserService {
    public User getUserByEmail (String email);
}
