package com.phuc.SWP391.service.serviceImpl;

import com.phuc.SWP391.model.Notification;
import com.phuc.SWP391.model.User;
import com.phuc.SWP391.model.dto.NotificationDto;
import com.phuc.SWP391.model.exception.ApiException;
import com.phuc.SWP391.repository.NotificationRepo;
import com.phuc.SWP391.repository.UserRepository;
import com.phuc.SWP391.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private SimpMessagingTemplate messagingTemplate;
    private UserRepository userRepository;
    private NotificationRepo notificationRepo;
    private ModelMapper modelMapper;

    public NotificationServiceImpl(ModelMapper modelMapper, NotificationRepo notificationRepo, UserRepository userRepository, SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.userRepository = userRepository;
        this.notificationRepo = notificationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void sendNotificationToUser(Long userId, String message) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Không tìm thấy người dùng"));
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepo.save(notification);

        messagingTemplate.convertAndSend(
                "/queue/notifications/" + userId,
                message
        );
    }

    @Override
    public void sendNotificationToManager(String message) {
        List<User> managers = userRepository.findByRole_Name("MANAGER");

        for (User manager : managers) {

            Notification notification = new Notification();
            notification.setUser(manager);
            notification.setMessage(message);
            notification.setRead(false);
            notification.setCreatedAt(LocalDateTime.now());

            notificationRepo.save(notification);

            // realtime
            messagingTemplate.convertAndSend(
                    "/topic/notifications/" + manager.getId(),
                    notification
            );
        }
    }

    @Override
    public List<NotificationDto> getAllNotification() {
        List<Notification> list = notificationRepo.findAll();
        List<NotificationDto> response = list.stream().map(c -> modelMapper.map(c, NotificationDto.class)).collect(Collectors.toList());
        return  response;
    }

    @Override
    public List<NotificationDto> getNotificationByUser(Long userId) {
        List<Notification> list = notificationRepo.findByUser_Id(userId);
        List<NotificationDto> response = list.stream().map(c -> modelMapper.map(c, NotificationDto.class)).collect(Collectors.toList());
        return  response;
    }
}
