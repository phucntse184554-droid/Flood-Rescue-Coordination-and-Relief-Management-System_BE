package repository;

import model.RescueRequest;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring tự tạo CRUD (save, findAll, delete...)
public interface RescueRequestRepo extends JpaRepository<RescueRequest, Long> {}
