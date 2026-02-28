package com.phuc.SWP391.model.entity;


import jakarta.persistence.*;

public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "access_token_id")
    private RefreshToken accessToken;
}
