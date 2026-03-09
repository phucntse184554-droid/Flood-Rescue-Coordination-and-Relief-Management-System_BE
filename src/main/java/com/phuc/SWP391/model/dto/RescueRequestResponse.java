package com.phuc.SWP391.model.dto;

import com.phuc.SWP391.model.EmergencyLevel;
import com.phuc.SWP391.model.RequestStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RescueRequestResponse {
    private Long id;
    private UserResponse citizen;
    private String location;
    private String description;
    @Enumerated(EnumType.STRING)
    private EmergencyLevel emergencyLevel;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private LocalDateTime createdAt;
}
