package com.projects.learning.dto;

import com.projects.learning.domain.ShipStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdateDTO {

    @NotNull(message = "El estado es obligatorio.")
    private ShipStatus status;
}
