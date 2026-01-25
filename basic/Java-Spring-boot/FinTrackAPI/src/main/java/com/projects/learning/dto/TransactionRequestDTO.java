package com.projects.learning.dto;

import com.projects.learning.domain.TransactionType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequestDTO {

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotNull(message = "La acntidad no puede ser nula")
    @Positive(message = "La cantidad tiene que ser mayor que cero")
    private BigDecimal amount;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotNull(message = "El tipo de transacción es obligatorio")
    private TransactionType transactionType;

    @NotNull(message = "La categoría es obligatoria")
    private String category;

}
