package com.phuc.SWP391.repository;

import com.phuc.SWP391.model.RescueAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Spring sẽ tự quản lý class này và cho phép inject vào Service
public interface RescueAssignmentRepo
        extends JpaRepository<RescueAssignment, Long> {
}