package com.phuc.SWP391.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "RescueTeam")
@Data
public class RescueTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;   // Tên đội cứu hộ
    private boolean available;
    // true = rảnh, false = đang làm nhiệm vụ
}
