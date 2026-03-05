package com.phuc.SWP391.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Vehicle")
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;   // Ambulance, Boat...
    private boolean available;
}