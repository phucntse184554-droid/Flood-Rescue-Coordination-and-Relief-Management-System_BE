package com.phuc.SWP391.controller;

import com.phuc.SWP391.model.RescueAssignment;
import com.phuc.SWP391.service.CompleteMissionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
public class CompletionController {

    private final CompleteMissionService service;

    public CompletionController(CompleteMissionService service) {
        this.service = service;
    }

    @PutMapping("/complete/{id}")
    public RescueAssignment complete(@PathVariable Long id) {
        return service.complete(id);
    }
}