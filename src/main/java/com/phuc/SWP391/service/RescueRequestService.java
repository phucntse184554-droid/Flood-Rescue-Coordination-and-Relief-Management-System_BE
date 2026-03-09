package com.phuc.SWP391.service;

import com.phuc.SWP391.model.dto.RescueRequestResponse;
import com.phuc.SWP391.model.RequestStatus;
import com.phuc.SWP391.model.payload.request.RescueRequestDto;

import java.util.List;

public interface RescueRequestService {
    RescueRequestResponse createRequest(RescueRequestDto rescueRequestDto);
    List<RescueRequestResponse> getMyRequests(Long userId);
    RescueRequestResponse confirmRescue(Long requestId, RequestStatus status);
}
