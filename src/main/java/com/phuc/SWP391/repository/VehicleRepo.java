package com.phuc.SWP391.repository;

import com.phuc.SWP391.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// Lấy xe chưa được giao
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByAvailableTrue();
}
