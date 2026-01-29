package com.projects.learning.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Starship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer crewCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipStatus status;

    @Column(nullable = false)
    private String currentQuadrant;

    @Column(nullable = false)
    private LocalDate lastMaintenance;
}
