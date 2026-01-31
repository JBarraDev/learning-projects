package com.projects.learning.dto;

import com.projects.learning.domain.ShipStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarshipRequestDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

    @NotBlank(message = "El modelo es obligatorio.")
    private String model;

    @NotNull
    @Min(value = 1, message = "La capacidad debe ser al menos de 1.")
    private Integer crewCapacity;

    @NotNull(message = "El estado de la nave es obligatorio.")
    private ShipStatus status;

    @NotBlank
    @Pattern(
            regexp = "^Sector-[0-9]+[A-Z]$",
            message = "Formato inválido. Use 'Sector-X' (ej: Sector-7G)"
    )
    private String currentQuadrant;

    @PastOrPresent(message = "La fecha no puede ser posterior a hoy.")
    private LocalDate lastMaintenance;

}
