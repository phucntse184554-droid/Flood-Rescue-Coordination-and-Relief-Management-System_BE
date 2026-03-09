package com.phuc.SWP391.repository;

import com.phuc.SWP391.model.Notification;
import com.phuc.SWP391.model.RescueRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    List<Notification> findByUser_Id(Long userId);}
