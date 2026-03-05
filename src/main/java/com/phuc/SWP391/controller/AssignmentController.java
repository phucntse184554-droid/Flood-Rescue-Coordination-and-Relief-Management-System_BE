package com.phuc.SWP391.controller;

import com.phuc.SWP391.model.RescueAssignment;
import org.springframework.web.bind.annotation.*;
import com.phuc.SWP391.service.CoordinatorService;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final CoordinatorService service;

    public AssignmentController(CoordinatorService service) {
        this.service = service;
    }

    @PostMapping("/assign")
    public RescueAssignment assign(
            @RequestParam Long requestId,
            @RequestParam Long teamId,
            @RequestParam Long vehicleId) {

        return service.assign(requestId, teamId, vehicleId);
    }
}