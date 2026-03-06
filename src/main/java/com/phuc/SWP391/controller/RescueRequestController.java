package com.phuc.SWP391.controller;

import com.phuc.SWP391.model.EmergencyLevel;
import com.phuc.SWP391.model.RescueRequest;
import com.phuc.SWP391.service.VerifyRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class RescueRequestController {

    private final VerifyRequestService service;

    public RescueRequestController(VerifyRequestService service) {
        this.service = service;
    }

    @PutMapping("/verify/{id}")
    public RescueRequest verify(
            @PathVariable Long id,
            @RequestParam EmergencyLevel level) {

        return service.verifyRequest(id, level);
    }
}
