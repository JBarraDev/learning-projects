package com.projects.learning.dto;

import com.projects.learning.domain.ShipStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StarShipResponseDTO {

    private String name;
    private String model;
    private Integer crewCapacity;
    private ShipStatus status;
    private String currentQuadrant;
    private LocalDate lastMaintenance;

}
