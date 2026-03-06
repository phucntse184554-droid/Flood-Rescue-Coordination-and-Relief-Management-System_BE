package com.phuc.SWP391.service;

import com.phuc.SWP391.model.*;
import com.phuc.SWP391.repository.RescueAssignmentRepo;
import org.springframework.stereotype.Service;

@Service
public class CompleteMissionService {

    private final RescueAssignmentRepo assignmentRepo;

    public CompleteMissionService(RescueAssignmentRepo assignmentRepo) {
        this.assignmentRepo = assignmentRepo;
    }

    public RescueAssignment complete(Long assignmentId) {

        RescueAssignment a = assignmentRepo.findById(assignmentId).orElseThrow();

        a.setStatus("COMPLETED");

        a.getTeam().setAvailable(true);
        a.getVehicle().setAvailable(true);

        a.getRequest().setStatus(RequestStatus.COMPLETED);

        return assignmentRepo.save(a);
    }
}