package com.projects.learning.dto;

import com.projects.learning.domain.TransactionType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 100, message = "La descripción no puede superar los 100 caracteres.")
    private String description;

    @NotNull(message = "La cantidad no puede ser nula")
    @Positive(message = "La cantidad tiene que ser mayor que cero")
    private BigDecimal amount;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotNull(message = "El tipo de transacción es obligatorio")
    private TransactionType transactionType;

    @NotNull(message = "La categoría es obligatoria")
    private String category;

}
