package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RescueAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // Nhiều assignment có thể dùng 1 request
    private RescueRequest request;

    @ManyToOne
    private RescueTeam team;

    @ManyToOne
    private Vehicle vehicle;

    private String status;
    // IN_PROGRESS / COMPLETED
}