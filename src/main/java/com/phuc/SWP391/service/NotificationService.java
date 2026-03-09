package com.phuc.SWP391.service;

import com.phuc.SWP391.model.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    void sendNotificationToUser(Long userId, String message);
    void sendNotificationToManager(String message);
    List<NotificationDto> getAllNotification ();
    List<NotificationDto> getNotificationByUser (Long userId);

}
