package controller;

import lombok.RequiredArgsConstructor;
import model.EmergencyLevel;
import model.RescueAssignment;
import model.RescueRequest;
import org.springframework.web.bind.annotation.*;
import service.CoordinatorService;

@RestController
// Đánh dấu class là REST API Controller
// Spring sẽ tạo endpoint HTTP

@RequestMapping("/coordinator")
// Tất cả API trong class sẽ bắt đầu bằng /coordinator
// Ví dụ: /coordinator/verify/1
public class CoordinatorController {

    private final CoordinatorService service;
    // Service dùng để xử lý logic điều phối

    public CoordinatorController(CoordinatorService service) {
        this.service = service;
        // Constructor injection → Spring tự truyền service vào
    }

    // ==============================
    // API dành cho Coordinator
    // ==============================
    @PutMapping("/verify/{id}")
    // API PUT request
    // URL: /coordinator/verify/1?level=HIGH
    public RescueRequest verify(
            @PathVariable Long id,
            // Lấy id từ URL

            @RequestParam EmergencyLevel level) {
        // Lấy level từ query param ?level=HIGH

        return service.verifyRequest(id, level);
        // Gọi CoordinatorService để xử lý logic
    }

    // ==============================
    // Điều phối team + xe
    // ==============================
    @PostMapping("/assign")
    public RescueAssignment assign(
            @RequestParam Long requestId, // Lấy requestId từ query parameter
            @RequestParam Long teamId, // Lấy teamId từ query parameter
            @RequestParam Long vehicleId) { // Lấy vehicleId từ query parameter
        return service.assign(requestId, teamId, vehicleId);
        // Gọi CoordinatorService để xử lý logic điều phối
    }

    // ==============================
    // Hoàn thành nhiệm vụ
    // ==============================
    @PutMapping("/complete/{id}")
    // Annotation tạo API dạng PUT
    // Dùng khi Coordinator xác nhận nhiệm vụ cứu hộ đã hoàn thành
    public RescueAssignment complete(@PathVariable Long id) {
        return service.complete(id);
        // Gọi CoordinatorService.complete()
    }
}