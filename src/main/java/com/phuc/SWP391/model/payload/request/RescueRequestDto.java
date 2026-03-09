package com.phuc.SWP391.model.payload.request;

import com.phuc.SWP391.model.EmergencyLevel;
import com.phuc.SWP391.model.RequestStatus;
import com.phuc.SWP391.model.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RescueRequestDto {
    private Long citizenId;
    private String location;
    private String description;

    @Enumerated(EnumType.STRING)
    private EmergencyLevel emergencyLevel;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
