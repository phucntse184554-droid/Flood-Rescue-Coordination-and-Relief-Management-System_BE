package model;

import jakarta.persistence.*;
import lombok.Data;
    @Entity // Đánh dấu class này là bảng trong database
    @Data   // Lombok tự tạo getter/setter/toString
    public class RescueRequest {

        @Id // Khóa chính
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        // Auto tăng ID
        private Long id;

        private String citizenName; // Người gửi yêu cầu
        private String location;    // Vị trí cứu hộ
        private String description; // Mô tả tình huống

        @Enumerated(EnumType.STRING)
        // Lưu enum dạng chữ trong DB (LOW, HIGH...)
        private EmergencyLevel emergencyLevel;

        @Enumerated(EnumType.STRING)
        private RequestStatus status; // Trạng thái request
    }
