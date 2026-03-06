package com.phuc.SWP391.service;

import com.phuc.SWP391.model.*;
import com.phuc.SWP391.repository.*;
import org.springframework.stereotype.Service;

@Service
public class AssignRescueService {

    private final RescueRequestRepo requestRepo;
    private final RescueTeamRepo teamRepo;
    private final VehicleRepo vehicleRepo;
    private final RescueAssignmentRepo assignmentRepo;

    public AssignRescueService(
            RescueRequestRepo requestRepo,
            RescueTeamRepo teamRepo,
            VehicleRepo vehicleRepo,
            RescueAssignmentRepo assignmentRepo) {

        this.requestRepo = requestRepo;
        this.teamRepo = teamRepo;
        this.vehicleRepo = vehicleRepo;
        this.assignmentRepo = assignmentRepo;
    }

    public RescueAssignment assign(Long requestId, Long teamId, Long vehicleId) {

        RescueRequest request = requestRepo.findById(requestId).orElseThrow();

        RescueTeam team = teamRepo.findById(teamId).orElseThrow();

        Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow();

        team.setAvailable(false);
        vehicle.setAvailable(false);

        request.setStatus(RequestStatus.ASSIGNED);

        RescueAssignment assignment = new RescueAssignment();

        assignment.setRequest(request);
        assignment.setTeam(team);
        assignment.setVehicle(vehicle);
        assignment.setStatus("IN_PROGRESS");

        return assignmentRepo.save(assignment);
    }
}