package com.phuc.SWP391.repository;

import com.phuc.SWP391.model.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AccessTokenRepository  extends JpaRepository<AccessToken, Long> {
}
