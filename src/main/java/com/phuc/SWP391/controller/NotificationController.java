package com.phuc.SWP391.controller;

import com.phuc.SWP391.model.Notification;
import com.phuc.SWP391.repository.NotificationRepo;
import com.phuc.SWP391.service.NotificationService;
import com.phuc.SWP391.utils.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private NotificationService notificationService;
    private NotificationRepo notificationRepo;

    public NotificationController(NotificationService notificationService, NotificationRepo notificationRepo) {
        this.notificationService = notificationService;
        this.notificationRepo = notificationRepo;
    }

    @GetMapping()
    public ResponseEntity<?> getNotifications(Authentication authentication){

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        return new ResponseEntity<>(notificationService.getNotificationByUser(user.getId()), HttpStatus.OK);
    }}


