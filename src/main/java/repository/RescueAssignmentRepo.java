package repository;

import model.RescueAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Spring sẽ tự quản lý class này và cho phép inject vào Service
public interface RescueAssignmentRepo
        extends JpaRepository<RescueAssignment, Long> {
        // RescueAssignment → Entity quản lý
        // Long → kiểu dữ liệu của Primary Key (id)

        // Nhờ JpaRepository có sẵn:
        // save()
        // findById()
        // findAll()
        // delete()
        // update()
}