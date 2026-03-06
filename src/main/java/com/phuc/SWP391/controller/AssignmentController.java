package com.phuc.SWP391.controller;

import com.phuc.SWP391.model.RescueAssignment;
import com.phuc.SWP391.service.AssignRescueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignRescueService service;

    public AssignmentController(AssignRescueService service) {
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