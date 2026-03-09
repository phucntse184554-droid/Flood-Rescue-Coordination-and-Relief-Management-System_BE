package com.phuc.SWP391.controller;

import com.phuc.SWP391.model.dto.RescueRequestResponse;
import com.phuc.SWP391.model.EmergencyLevel;
import com.phuc.SWP391.model.RequestStatus;
import com.phuc.SWP391.model.RescueRequest;
import com.phuc.SWP391.model.payload.request.RescueRequestDto;
import com.phuc.SWP391.service.RescueRequestService;
import com.phuc.SWP391.service.VerifyRequestService;
import com.phuc.SWP391.utils.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rescue")
public class RescueRequestController {

    private final VerifyRequestService service;
    private RescueRequestService rescueRequestService;

    public RescueRequestController(VerifyRequestService service, RescueRequestService rescueRequestService) {
        this.service = service;
        this.rescueRequestService = rescueRequestService;
    }

    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody RescueRequestDto dto,
                                                       Authentication authentication) {

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        RescueRequestResponse response = rescueRequestService.createRequest(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyRequests(Authentication authentication){

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        List<RescueRequestResponse> response = rescueRequestService.getMyRequests(user.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<?> confirmRescue (@PathVariable Long id, RequestStatus status){
        RescueRequestResponse response =  rescueRequestService.confirmRescue(id, status);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/verify/{id}")
    public RescueRequest verify(
            @PathVariable Long id,
            @RequestParam EmergencyLevel level) {

        return service.verifyRequest(id, level);
    }
}
