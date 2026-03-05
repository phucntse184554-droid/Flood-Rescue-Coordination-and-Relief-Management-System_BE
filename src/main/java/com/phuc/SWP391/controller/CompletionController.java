package com.phuc.SWP391.controller;

import com.phuc.SWP391.model.RescueAssignment;
import org.springframework.web.bind.annotation.*;
import com.phuc.SWP391.service.CoordinatorService;

@RestController
@RequestMapping("/missions")
public class CompletionController {

    private final CoordinatorService service;

    public CompletionController(CoordinatorService service) {
        this.service = service;
    }

    @PutMapping("/complete/{id}")
    public RescueAssignment complete(@PathVariable Long id) {
        return service.complete(id);
    }
}