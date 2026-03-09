package com.phuc.SWP391.model;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private String avatar;

    private Boolean isVerified;

    private String fullName;

    @OneToMany(mappedBy = "user")
    private List<AccessToken> accessToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notification;
}
