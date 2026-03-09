package com.phuc.SWP391.repository;

import com.phuc.SWP391.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);
    Boolean existsByPhone(String email);
    List<User> findByRole_Name(String roleName);
}
