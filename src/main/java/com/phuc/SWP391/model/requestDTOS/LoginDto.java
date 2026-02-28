package com.phuc.SWP391.model.requestDTOS;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    String username;
    String password;
}
