package com.phuc.SWP391.model.payload.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @NotEmpty(message = "Phone can not be empty")
    private String phone;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "Fullname can not be empty")
    private String fullName;
}
