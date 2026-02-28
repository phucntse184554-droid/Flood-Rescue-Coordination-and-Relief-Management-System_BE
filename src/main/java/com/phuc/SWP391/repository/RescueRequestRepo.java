package com.phuc.SWP391.repository;

import com.phuc.SWP391.model.entity.RescueRequest;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring tự tạo CRUD (save, findAll, delete...)
public interface RescueRequestRepo extends JpaRepository<RescueRequest, Long> {}
