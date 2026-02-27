package repository;

import model.RescueTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RescueTeamRepo extends JpaRepository<RescueTeam, Long> {
    List<RescueTeam> findByAvailableTrue();
    // Lấy team chưa được giao
}