package com.phuc.SWP391.service;

import com.phuc.SWP391.model.*;
import com.phuc.SWP391.repository.RescueRequestRepo;
import org.springframework.stereotype.Service;

@Service
public class VerifyRequestService {

    private final RescueRequestRepo requestRepo;

    public VerifyRequestService(RescueRequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    public RescueRequest verifyRequest(Long id, EmergencyLevel level) {

        RescueRequest request = requestRepo.findById(id).orElseThrow();

        request.setEmergencyLevel(level);

        request.setStatus(RequestStatus.VERIFIED);

        return requestRepo.save(request);
    }
}
