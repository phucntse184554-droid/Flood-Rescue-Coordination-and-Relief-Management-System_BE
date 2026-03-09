package com.phuc.SWP391.model.dto;

import com.phuc.SWP391.model.Notification;
import com.phuc.SWP391.model.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String phone;
    private Role role;
    private String avatar;
    private Boolean isVerified;
    private String fullName;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<NotificationDto> notification;
}
