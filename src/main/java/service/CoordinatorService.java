package service;

import model.*;
import repository.*;
import org.springframework.stereotype.Service;
import repository.RescueRequestRepo;

@Service
// Annotation này đánh dấu class là Service → Spring tự quản lý object này
// Thuộc Service layer trong mô hình MVC
public class CoordinatorService {

    private final RescueRequestRepo requestRepo;
    // Repository để đọc/ghi RescueRequest trong database

    private final RescueTeamRepo teamRepo;
    // Repository để quản lý RescueTeam

    private final VehicleRepo vehicleRepo;
    // Repository để quản lý Vehicle

    private final RescueAssignmentRepo assignmentRepo;
    // Repository để lưu assignment sau khi điều phối

    public CoordinatorService(
            RescueRequestRepo requestRepo,
            RescueTeamRepo teamRepo,
            VehicleRepo vehicleRepo,
            RescueAssignmentRepo assignmentRepo) {

        this.requestRepo = requestRepo;
        this.teamRepo = teamRepo;
        this.vehicleRepo = vehicleRepo;
        this.assignmentRepo = assignmentRepo;
        // Constructor Injection → Spring tự truyền repo vào
    }

    // ==============================
    // 1. Verify Request
    // ==============================
    public RescueRequest verifyRequest(Long id, EmergencyLevel level) {

        RescueRequest request = requestRepo.findById(id).orElseThrow();
        // Tìm RescueRequest theo ID
        // Nếu không tồn tại → ném lỗi

        request.setEmergencyLevel(level);
        // Gán mức độ khẩn cấp (LOW, MEDIUM, HIGH, CRITICAL)

        request.setStatus(RequestStatus.VERIFIED);
        // Đổi trạng thái sang VERIFIED
        // Nghĩa là Coordinator đã kiểm tra yêu cầu

        return requestRepo.save(request);
        // Lưu lại database và trả về request đã cập nhật
    }

    // ==============================
    // 2. Assign Team + Vehicle
    // ==============================
    public RescueAssignment assign(Long requestId, Long teamId, Long vehicleId) {

        RescueRequest request = requestRepo.findById(requestId).orElseThrow();
        // Lấy request cần cứu hộ

        RescueTeam team = teamRepo.findById(teamId).orElseThrow();
        // Lấy đội cứu hộ

        Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow();
        // Lấy phương tiện

        team.setAvailable(false);
        // Đánh dấu team đang bận

        vehicle.setAvailable(false);
        // Đánh dấu xe đang bận

        request.setStatus(RequestStatus.ASSIGNED);
        // Cập nhật trạng thái request đã được điều phối

        RescueAssignment assignment = new RescueAssignment();
        // Tạo assignment mới

        assignment.setRequest(request);
        assignment.setTeam(team);
        assignment.setVehicle(vehicle);
        assignment.setStatus("IN_PROGRESS");
        // Nhiệm vụ đang thực hiện

        return assignmentRepo.save(assignment);
        // Lưu assignment vào database
    }

    // ==============================
    // 3. Complete Mission
    // ==============================
    public RescueAssignment complete(Long assignmentId) {

        RescueAssignment a = assignmentRepo.findById(assignmentId).orElseThrow();

        a.setStatus("COMPLETED");

        a.getTeam().setAvailable(true);
        a.getVehicle().setAvailable(true);
        // Giải phóng tài nguyên

        a.getRequest().setStatus(RequestStatus.COMPLETED);

        return assignmentRepo.save(a);
    }
}