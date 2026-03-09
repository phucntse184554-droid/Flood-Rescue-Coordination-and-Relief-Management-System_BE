package com.phuc.SWP391.repository;

import com.phuc.SWP391.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccessTokenRepo extends JpaRepository<AccessToken, Integer> {
    @Query("""
                        select t from AccessToken t
                        inner join User u on u.id = t.user.id
                        where u.id = :userId and (t.expired = false or t.revoked = false)
            """)
    List<AccessToken> findAllValidTokensByUser(Long userId);

    AccessToken findByToken(String accessToken);
}
