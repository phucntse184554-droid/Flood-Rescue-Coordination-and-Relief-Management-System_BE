package com.phuc.SWP391.service.serviceImpl;

import com.phuc.SWP391.model.dto.RescueRequestResponse;
import com.phuc.SWP391.model.RequestStatus;
import com.phuc.SWP391.model.RescueRequest;
import com.phuc.SWP391.model.User;
import com.phuc.SWP391.model.payload.request.RescueRequestDto;
import com.phuc.SWP391.repository.RescueRequestRepo;
import com.phuc.SWP391.repository.UserRepository;
import com.phuc.SWP391.service.NotificationService;
import com.phuc.SWP391.service.RescueRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RescueRequestServiceImpl implements RescueRequestService {

    private RescueRequestRepo rescueRequestRepository;
    private UserRepository userRepository;
    private NotificationService notificationService;
    private ModelMapper modelMapper;

    public RescueRequestServiceImpl(ModelMapper modelMapper, RescueRequestRepo rescueRequestRepository, UserRepository userRepository, NotificationService notificationService) {
        this.rescueRequestRepository = rescueRequestRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public RescueRequestResponse createRequest(RescueRequestDto rescueRequestDto) {
        User user = userRepository.findById(rescueRequestDto.getCitizenId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        RescueRequest request = new RescueRequest();
        request.setLocation(rescueRequestDto.getLocation());
        request.setDescription(rescueRequestDto.getDescription());
        request.setStatus(RequestStatus.PENDING);
        request.setCreatedAt(LocalDateTime.now());
        request.setCitizen(user);
        notificationService.sendNotificationToManager(
                "Có yêu cầu cứu hộ mới tại " + request.getLocation()
        );
        RescueRequest request1 = rescueRequestRepository.save(request);
        return modelMapper.map(request1, RescueRequestResponse.class);

    }

    @Override
    public List<RescueRequestResponse> getMyRequests(Long userId) {
        List<RescueRequest> list = rescueRequestRepository.findByCitizenId(userId);
        List<RescueRequestResponse> list2 = list.stream().map(c -> modelMapper.map(c, RescueRequestResponse.class)).collect(Collectors.toList());
        return list2;
    }

    @Override
    public RescueRequestResponse confirmRescue(Long requestId, RequestStatus status) {
        RescueRequest request = rescueRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(status);
        notificationService.sendNotificationToUser(request.getCitizen().getId(), "Yêu cầu của bạn đã được cập nhật thành " + status);
        RescueRequest request1 = rescueRequestRepository.save(request);
        return modelMapper.map(request1, RescueRequestResponse.class);
    }
}
