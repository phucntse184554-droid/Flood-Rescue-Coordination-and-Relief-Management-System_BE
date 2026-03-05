package com.phuc.SWP391.controller;

import com.phuc.SWP391.model.EmergencyLevel;
import com.phuc.SWP391.model.RescueRequest;
import org.springframework.web.bind.annotation.*;
import com.phuc.SWP391.service.CoordinatorService;

@RestController
@RequestMapping("/requests")
public class RescueRequestController {

    private final CoordinatorService service;

    public RescueRequestController(CoordinatorService service) {
        this.service = service;
    }

    @PutMapping("/verify/{id}")
    public RescueRequest verify(
            @PathVariable Long id,
            @RequestParam EmergencyLevel level) {

        return service.verifyRequest(id, level);
    }
}
